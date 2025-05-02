package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class ShoppingService {

    @Autowired
    UserService userService;

    @Autowired
    SessionCartService sessionCartService;

    public void loggedInUser(User existinguUser) {
        userService.loggedInUser(existinguUser);
    }

    public User getLoggedInUser() {
        return userService.getCurrentUser();
    }
}
