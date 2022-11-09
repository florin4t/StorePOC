package com.management.store.rest.controller;

import com.management.store.model.entity.StoreProduct;
import com.management.store.rest.service.StoreProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return storeProductService.getStoreProducts();
    }

}
