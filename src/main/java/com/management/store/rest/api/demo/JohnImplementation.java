package com.management.store.rest.api.demo;

import com.management.store.model.entity.StoreProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/product")
public non-sealed class JohnImplementation extends StoreManagementCertifiedImplementations {
    @Override
    public List<StoreProduct> products() {
        return null;
    }

    @Override
    public StoreProduct addProduct(StoreProduct newProduct) {
        return super.addProduct(newProduct);
    }

    @Override
    public ResponseEntity<List<StoreProduct>> find(String productId) {
        return super.find(productId);
    }

    @Override
    public List<StoreProduct> updateProduct(String productId, StoreProduct updatedProduct) {
        return super.updateProduct(productId, updatedProduct);
    }

    @Override
    public ResponseEntity<String> deleteProduct(String productId) {
        return super.deleteProduct(productId);
    }

    @Override
    public ResponseEntity<String> deleteAllProducts() {
        return super.deleteAllProducts();
    }
}
