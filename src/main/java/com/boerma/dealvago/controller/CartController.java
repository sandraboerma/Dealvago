package com.boerma.dealvago.controller;

import com.boerma.dealvago.service.SessionCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private SessionCartService sessionCartService;

    public CartController(SessionCartService sessionCartService) {
        this.sessionCartService = sessionCartService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity) {
        logger.info("Adding product using Id {} and quantity {} to the cart", productId, quantity);
        sessionCartService.addOrderline(productId, quantity);
        return "redirect:/products";
    }
}
