package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.dto.ProductDto;
import com.boerma.dealvago.service.InventoryService;
import com.boerma.dealvago.service.SessionCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final InventoryService inventoryService;
    private final SessionCartService sessionCartService;

    public ProductController(InventoryService inventoryService, SessionCartService sessionCartService) {
        this.inventoryService = inventoryService;
        this.sessionCartService = sessionCartService;
    }

    @GetMapping("/products")
    public String showOrSearchProducts(@RequestParam(value = "search", required = false) String search, Model model) {
        logger.info("GET /products received with search='{}'", search);
        List<ProductDto> products;
        if (search != null && !search.isBlank()) {
            products = inventoryService.getProducts(search);
        } else {
            products = inventoryService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("cart", sessionCartService.getOrderlines());
        model.addAttribute("totalPrice", sessionCartService.calculateTotalPrice());
        return "customerpage";
    }


}
