package com.management.store.rest.controller;

import com.management.store.model.entity.StoreProduct;
import com.management.store.rest.service.StoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StoreProduct addProduct(@RequestBody @Valid StoreProduct newProduct) {
        return this.storeProductService.addProduct(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StoreProduct>> find(@PathVariable(name = "id") String productId) {
        return new ResponseEntity<>(this.storeProductService.getProduct(productId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public List<StoreProduct> updateProduct(@PathVariable(name = "id") String productId,
                                            @RequestBody @Validated StoreProduct updatedProduct) {
        return this.storeProductService.updateProduct(productId, updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") String productId) {
        this.storeProductService.deleteProduct(productId);
        return new ResponseEntity<>(String.format("Deleted product with ID %s", productId), HttpStatus.OK);
    }


}
