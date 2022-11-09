package com.management.store.rest.controller;

import com.management.store.model.entity.StoreProduct;
import com.management.store.rest.service.StoreProductService;
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

}
