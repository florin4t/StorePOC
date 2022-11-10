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
        log.info("Retrieving all products from the store.");
        return productRepository.findAll();
    }

    public StoreProduct addProduct(StoreProduct newProduct) {
        StoreProduct newProd = this.productRepository.save(newProduct);
        this.log.info("Adding a new product to the store: {}", newProd);
        return newProd;
    }

    public List<StoreProduct> getProduct(String productId) {
        log.info("Retrieving product with id {}", productId);
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
        log.info("Updating product with id {} to value {}", productId, updatedProduct);
        StoreProduct existingProduct = this.findProduct(productId);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCurrency(updatedProduct.getCurrency());
        return List.of(this.productRepository.save(existingProduct));
    }

    public void deleteProduct(String productId) {
        log.info("Deleting product with id {}", productId);
        this.productRepository.delete(this.findProduct(productId));
    }

    private StoreProduct findProduct(String productId) {
        List<StoreProduct> productMatches = this.getProduct(productId);
        return switch (productMatches.size()) {
            case 0 -> {
                log.error("The find product operation for id {} returned multiple results.", productId);
                throw new IllegalArgumentException("Product not found.");
            }
            case 1 -> productMatches.get(0);
            default -> {
                log.error("The find product operation for id {} returned multiple results.", productId);
                throw new IllegalStateException("Product search by ID returned multiple results.");
            }
        };
    }
}

