package tuanPtit.ElasticSearchSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "tuanPtit.ElasticSearchSpringBoot.repo")
public class ElasticSearchSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchSpringBootApplication.class, args);
	}

}
