package com.management.store.rest.controller;

import com.management.store.model.entity.StoreProduct;
import com.management.store.rest.api.StoreProductManagementApi;
import com.management.store.rest.service.StoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class StoreProductController implements StoreProductManagementApi {

    private final StoreProductService storeProductService;

    public StoreProductController(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping("/all")
    public List<StoreProduct> products() {
        return this.storeProductService.getStoreProducts();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StoreProduct addProduct(@RequestBody @Valid StoreProduct newProduct) {
        return this.storeProductService.addProduct(newProduct);
    }

    @Override
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping("/{id}")
    public ResponseEntity<List<StoreProduct>> find(@PathVariable(name = "id") String productId) {
        return new ResponseEntity<>(this.storeProductService.getProduct(productId), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public List<StoreProduct> updateProduct(@PathVariable(name = "id") String productId,
                                            @RequestBody @Validated StoreProduct updatedProduct) {
        return this.storeProductService.updateProduct(productId, updatedProduct);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") String productId) {
        this.storeProductService.deleteProduct(productId);
        return new ResponseEntity<>(String.format("Deleted product with ID %s", productId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAllProducts() {
        return StoreProductManagementApi.super.deleteAllProducts();
    }
}
