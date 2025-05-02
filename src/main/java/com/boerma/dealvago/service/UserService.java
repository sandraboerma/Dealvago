package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.entity.User;
import com.boerma.dealvago.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Service
@SessionScope
public class UserService {

    private final UserRepository userRepository;
    private User user;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void loggedInUser(User existingUser) {
        this.user = existingUser;
    }

    public User registerUser(String username, String password, String email) {
        user = new User(username, password, email, false);
        user = userRepository.save(user);
        return user;
    }

    public User getCurrentUser() {
        return user;
    }
}
