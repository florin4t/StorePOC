package com.management.store.app;

import com.management.store.model.entity.StoreProduct;
import com.management.store.model.repo.StoreProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = StoreProductRepository.class)
@EntityScan(basePackageClasses = StoreProduct.class)
@ComponentScan(basePackages = {"com.management.store.rest"})
public class ManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementAppApplication.class, args);
    }

}
