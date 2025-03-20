package com.boerma.dealvago;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductDAO {
    Optional<Product> getById(int productId);
    List<Product> getAllProducts();
    List<Product> getByName(String name);
    List<Product> getByCategory(String category);
}
