package com.management.store.rest.service;

import com.management.store.model.entity.StoreProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreProductService {
    public List<StoreProduct> getStoreProducts() {
        return List.of(new StoreProduct());
    }
}
