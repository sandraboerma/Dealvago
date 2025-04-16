package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminManagement(String view, Model model) {
        model.addAttribute("view", view);
        return "adminpage";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(String name, int unitPrice, int stock) {
        //placeholder for adding product
        return "adminpage";
    }
}
