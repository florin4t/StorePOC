package com.management.store.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.management.store.model", "com.management.store.rest"})
public class ManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementAppApplication.class, args);
    }

}
