package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.dto.ProductDto;
import com.boerma.dealvago.service.InventoryService;
import com.boerma.dealvago.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final InventoryService inventoryService;
    private final OrderService orderService;

    public AdminController(InventoryService inventoryService, OrderService orderService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String adminManagement(String view, Model model) {
        model.addAttribute("view", view);

        if ("products".equals(view)) {
            model.addAttribute("products", inventoryService.getAllProducts());
        } else {
            model.addAttribute("orders", orderService.getAllOrders());
        }

        return "adminpage";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(ProductDto productDto) {
        inventoryService.addProduct(productDto);
        return "redirect:/admin?view=products";
    }

    @PostMapping("/admin/products/update")
    public String updateProduct(int productId, int quantity) {
        inventoryService.updateProductStock(productId, quantity);
        return "redirect:/admin?view=products";
    }
}
