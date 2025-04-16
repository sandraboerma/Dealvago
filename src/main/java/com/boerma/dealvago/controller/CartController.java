package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import com.boerma.dealvago.service.SessionCartService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private SessionCartService sessionCartService;

    public CartController(SessionCartService sessionCartService) {
        this.sessionCartService = sessionCartService;
    }

    @PostMapping("/cart/add")
    public String addToCart(int productId, int quantity) {
        logger.info("Adding product using Id {} and quantity {} to the cart", productId, quantity);
        sessionCartService.addOrderline(productId, quantity);
        return "redirect:/products";
    }

    @PostMapping("/cart/update")
    public String updateCart(int productId, int quantity) {
        logger.info("Updating product using Id {} and quantity {} in the cart", productId, quantity);
        sessionCartService.updateQuantity(productId, quantity);
        return "redirect:/products";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(int productId) {
        logger.info("Removing product using Id {} from the cart", productId);
        sessionCartService.removeOrderline(productId);
        return "redirect:/products";
    }

    @PostMapping("/cart/checkout")
    public String checkout(Model model, HttpSession session) {
        logger.info("Checking out the cart");
        Integer userId = (Integer) session.getAttribute("loggedInUser");
        sessionCartService.checkout(userId);

        List<OrderlineDto> cartSnapshot = sessionCartService.getOrderlines();
        model.addAttribute("confirmedOrder", cartSnapshot);
        model.addAttribute("totalPrice", sessionCartService.calculateTotalPrice());

        sessionCartService.clear();

        return "orderconfirmationpage";
    }
}
