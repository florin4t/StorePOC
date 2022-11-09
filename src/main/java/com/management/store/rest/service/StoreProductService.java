package com.management.store.rest.service;

import com.management.store.model.entity.StoreProduct;
import com.management.store.model.repo.StoreProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreProductService {
    private final StoreProductRepository productRepository;

    public StoreProductService(StoreProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<StoreProduct> getStoreProducts() {
        return List.of(new StoreProduct());
    }

    public StoreProduct addProduct(StoreProduct newProduct) {
        return this.productRepository.save(newProduct);
    }
}
