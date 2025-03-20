package com.boerma.dealvago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SessionCartTest {

    SessionCart sessionCart;

    @BeforeEach
    void setUp() {
        sessionCart = new SessionCart();
        sessionCart.setProductDAO(new MockProductDAO());
    }

    @Test
    void addProduct() {
        assertTrue(sessionCart.addProduct(3));
        assertFalse(sessionCart.addProduct(-5));
        assertEquals(1, sessionCart.size());
    }

    @Test
    void addOrderline() {
        sessionCart.addProduct(3);
        assertEquals(1, sessionCart.size());
    }

    @Test
    void removeOrderline() {
        sessionCart.addProduct(3);
        sessionCart.addProduct(7);
        sessionCart.removeOrderline(3);
        assertEquals(1, sessionCart.size());
    }

    @Test
    void calculateOrderlinePrice() {
        sessionCart.calculateOrderlinePrice(2);
        assertEquals(30, sessionCart.calculateOrderlinePrice(2));
    }

    @Test
    void calculateTotalPrice() {
        sessionCart.addProduct(3); //15
        sessionCart.addProduct(7); //18
        assertEquals(33, sessionCart.calculateTotalPrice());
    }

    @Test
    void size() {
        assertEquals(0, sessionCart.size());
        sessionCart.addProduct(7);
        assertEquals(1, sessionCart.size());
        sessionCart.addProduct(3);
        assertEquals(2, sessionCart.size());
    }

    @Test
    void clear() {
        sessionCart.addProduct(7);
        sessionCart.clear();
        assertEquals(1, sessionCart.size());
    }

}

class MockProductDAO implements ProductDAO {
    @Override
    public Optional<Product> getById(int productId) {
        if (productId == 3) {
            return Optional.of(new Product(15, "mjölk", 3));
        } else if (productId == 7) {
            return Optional.of(new Product(18, "limpa", 7));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> getByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getByCategory(String category) {
        return List.of();
    }
}
