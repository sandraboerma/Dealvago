package com.boerma.dealvago.controller;

import com.boerma.dealvago.domain.entity.User;
import com.boerma.dealvago.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "loginpage";
    }

    @PostMapping("/login")
    public String loginUser(String username, String password, Model model, HttpSession session) {
        Optional<User> userOptional = userService.findUserByCredentials(username, password);

        if (userOptional.isEmpty()) {
            model.addAttribute("loginmessage", "Invalid username or password. " +
                    "Please try again or contact admin for assistance.");
            return "loginpage";
        }

        User existinguUser = userOptional.get();
        session.setAttribute("loggedInUser", existinguUser.getId());
        return existinguUser.isAdmin() ? "redirect:/admin" : "redirect:/products";
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String email, Model model, HttpSession session) {
        Optional<User> userOptional = userService.findUserByCredentials(username, password);

        if (userOptional.isPresent()) {
            model.addAttribute("registermessage", "User already exists. " +
                    "Please login or register with a different username.");
            return "loginpage";
        }

        User newUser = userService.registerUser(username, password, email);
        session.setAttribute("loggedInUser", newUser.getId());

        return "redirect:/products";
    }
}
