package tuanPtit.ElasticSearchSpringBoot.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import tuanPtit.ElasticSearchSpringBoot.entity.Product;
import tuanPtit.ElasticSearchSpringBoot.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public ProductService() {
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<Product> productIterable = productRepository.findAll();
        productIterable.forEach(product -> {
            products.add(product);
        });
        return products;
    }

    public List<Product> getBetweenPrice(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> getBetweenPriceV2(double minPrice, double maxPrice) {
        Criteria criteria = new Criteria("price").greaterThan(minPrice).lessThan(maxPrice);
        Query query = new CriteriaQuery(criteria);
        SearchHits<Product> searchHits = operations.search(query, Product.class);
        return searchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }

    public List<Product> findByNameV2(String name) {
        String queryJson = String.format(
                "{ \"multi_match\": { \"query\": \"%s\", \"fields\": [\"name\"] } }",
                name
        );

        Query query = new StringQuery(queryJson);
        SearchHits<Product> searchHits = operations.search(query, Product.class);
        return searchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }


}


