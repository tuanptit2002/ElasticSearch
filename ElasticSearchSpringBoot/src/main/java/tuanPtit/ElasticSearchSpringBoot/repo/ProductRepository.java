package tuanPtit.ElasticSearchSpringBoot.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tuanPtit.ElasticSearchSpringBoot.entity.Product;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByName(String name);

}
