package com.management.store.rest.controller;

import com.management.store.model.entity.StoreProduct;
import com.management.store.rest.service.StoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class StoreProductController {

    private final StoreProductService storeProductService;

    public StoreProductController(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @GetMapping("/all")
    public List<StoreProduct> products() {
        return this.storeProductService.getStoreProducts();
    }

    @PostMapping("/add")
    public StoreProduct addProduct(@RequestBody StoreProduct newProduct) {
        return this.storeProductService.addProduct(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StoreProduct>> find(@PathVariable(name = "id") String productId) {
        return new ResponseEntity<>(this.storeProductService.getProduct(productId), HttpStatus.OK);
    }
}
