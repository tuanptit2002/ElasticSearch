package tuanPtit.ElasticSearchSpringBoot.controller;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tuanPtit.ElasticSearchSpringBoot.entity.Product;
import tuanPtit.ElasticSearchSpringBoot.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private final Faker faker = new Faker();

    @PostMapping("/get-all")
    public List<Product> getAll(){
        return  productService.getProducts();
    }
    @PostMapping("/get-between-price")
    public List<Product> getAllByBetweenPrice(@RequestParam("minPrice") double minPrice,
                                @RequestParam("maxPrice") double maxPrice){
        return  productService.getBetweenPriceV2(minPrice, maxPrice);
    }

    @PostMapping("/get-by-name")
    public List<Product> getAllByName(@RequestParam("name") String name){
        return  productService.findByNameV2(name);
    }

}
