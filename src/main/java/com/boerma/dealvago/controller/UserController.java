package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.entity.User;
import com.boerma.dealvago.service.ShoppingService;
import com.boerma.dealvago.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private ShoppingService shoppingService;

    public UserController(UserService userService, ShoppingService shoppingService) {
        this.userService = userService;
        this.shoppingService = shoppingService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "loginpage";
    }

    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        Optional<User> userOptional = userService.findUserByCredentials(username, password);

        if (userOptional.isEmpty()) {
            model.addAttribute("loginmessage", "Invalid username or password. " +
                    "Please try again or contact admin for assistance.");
            return "loginpage";
        }

        User existinguUser = userOptional.get();
        shoppingService.loggedInUser(existinguUser);
        return existinguUser.isAdmin() ? "redirect:/admin" : "redirect:/products";
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String email, Model model) {
        Optional<User> userOptional = userService.findUserByCredentials(username, password);

        if (userOptional.isPresent()) {
            model.addAttribute("registermessage", "User already exists. " +
                    "Please login or register with a different username.");
            return "loginpage";
        }

        User newUser = userService.registerUser(username, password, email);
        shoppingService.loggedInUser(newUser);

        return "redirect:/products";
    }
}
