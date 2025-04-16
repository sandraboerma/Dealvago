package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.entity.User;
import com.boerma.dealvago.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User registerUser(String username, String password, String email) {
        User user = new User(username, password, email, false);
        return userRepository.save(user);
    }
}
