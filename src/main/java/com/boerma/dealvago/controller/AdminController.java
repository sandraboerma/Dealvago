package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.dto.ProductDto;
import com.boerma.dealvago.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final InventoryService inventoryService;

    public AdminController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/admin")
    public String adminManagement(String view, Model model) {
        model.addAttribute("view", view);

        if ("products".equals(view)) {
            List<ProductDto> products = inventoryService.getAllProducts();
            model.addAttribute("products", products);
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
