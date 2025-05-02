package com.boerma.dealvago.repository;

import com.boerma.dealvago.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    String findEmailById(Integer id);
}
