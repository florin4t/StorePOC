package com.management.store.rest.controller;

import com.management.store.model.entity.StoreProduct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class StoreProductController {

    @GetMapping("/all")
    public List<StoreProduct> products() {
        return List.of(new StoreProduct());
    }
}
