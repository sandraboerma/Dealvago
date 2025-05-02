package com.boerma.dealvago.controller;


import com.boerma.dealvago.domain.entity.Product;
import com.boerma.dealvago.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProuductRestController {

    private final InventoryService inventoryService;

    public ProuductRestController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        Optional<Product> product = inventoryService.getProduct(name);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(product.get());
        }
    }

}
