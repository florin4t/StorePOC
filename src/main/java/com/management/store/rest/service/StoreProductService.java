package com.management.store.rest.service;

import com.management.store.model.entity.StoreProduct;
import com.management.store.model.repo.StoreProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreProductService {
    private final StoreProductRepository productRepository;

    public StoreProductService(StoreProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<StoreProduct> getStoreProducts() {
        return productRepository.findAll();
    }

    public StoreProduct addProduct(StoreProduct newProduct) {
        StoreProduct newProd = this.productRepository.save(newProduct);
        this.log.info("Added a new product to the store: {}", newProd);
        return newProd;
    }

    public StoreProduct getProduct(String productId) {
        try {
            long id = Long.parseLong(productId);
            return productRepository.findById(id).orElse(null);
        } catch (NullPointerException | NumberFormatException e) {
            log.error("Invalid product ID. Cannot search by {}", productId);
            throw new IllegalArgumentException("Invalid product ID supplied");
        }
    }
}

