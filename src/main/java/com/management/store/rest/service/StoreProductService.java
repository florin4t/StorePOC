package com.management.store.rest.service;

import com.management.store.model.entity.StoreProduct;
import com.management.store.model.repo.StoreProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<StoreProduct> getProduct(String productId) {
        try {
            long id = Long.parseLong(productId);
            List<StoreProduct> searchResults = new ArrayList<>();
            productRepository.findById(id).ifPresentOrElse(searchResults::add, () -> {
            });
            return searchResults;
        } catch (NullPointerException | NumberFormatException e) {
            log.error("Invalid product ID. Cannot search by {}", productId);
            throw new IllegalArgumentException("Invalid product ID supplied");
        }
    }

    public List<StoreProduct> updateProduct(String productId, StoreProduct updatedProduct) {
        List<StoreProduct> productMatches = this.getProduct(productId);
        switch (productMatches.size()) {
            case 0 -> throw new IllegalArgumentException("Update failed, product not found.");
            case 1 -> {
                StoreProduct existingProduct = productMatches.get(0);
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setCurrency(updatedProduct.getCurrency());
                return List.of(this.productRepository.save(existingProduct));
            }
            default -> {
                log.error("The find product operation for id {} returned multiple results.", productId);
                throw new IllegalStateException("Product search by ID returned multiple results.");
            }
        }
    }
}

