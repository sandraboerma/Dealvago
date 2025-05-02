package com.boerma.dealvago.repository;

import com.boerma.dealvago.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int id);
    Optional<Product> findByName(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
}
