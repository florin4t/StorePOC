package com.management.store.rest.api;

import com.management.store.model.entity.StoreProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface StoreProductManagementApi {
    private static IllegalStateException missingImplementationException() {
        return new IllegalStateException("Operation not implemented!");
    }

    List<StoreProduct> products();

    default StoreProduct addProduct(@RequestBody @Valid StoreProduct newProduct) {
        throw missingImplementationException();
    }

    default ResponseEntity<List<StoreProduct>> find(@PathVariable(name = "id") String productId) {
        throw missingImplementationException();
    }

    default List<StoreProduct> updateProduct(@PathVariable(name = "id") String productId,
                                             @RequestBody @Validated StoreProduct updatedProduct) {
        throw missingImplementationException();
    }

    default ResponseEntity<String> deleteProduct(@PathVariable(name = "id") String productId) {
        throw missingImplementationException();
    }

    @DeleteMapping("/delete-all")
    @PreAuthorize("hasRole('ADMIN')")
    default ResponseEntity<String> deleteAllProducts() {
        throw missingImplementationException();
    }
}
