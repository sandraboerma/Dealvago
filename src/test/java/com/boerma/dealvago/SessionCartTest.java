package com.boerma.dealvago;

import com.boerma.dealvago.domain.entity.Product;
import com.boerma.dealvago.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class SessionCartTest {

    SessionCart sessionCart;

    @BeforeEach
    void setUp() {
        sessionCart = new SessionCart(new MockProductRepository());
    }

    @Test
    void addOrderline() {
        sessionCart.addOrderline(3, 2);
        assertEquals(1, sessionCart.size());
        assertEquals(3, sessionCart.getOrderlines().get(0).getProduct().getId());
    }

    @Test
    void removeOrderline() {
        sessionCart.addOrderline(3, 1);
        sessionCart.addOrderline(7, 1);
        sessionCart.removeOrderline(3);
        assertEquals(1, sessionCart.size());
    }

    @Test
    void calculateOrderlinePrice() {
        Product product = new Product(7, "sample2", 18, 10);
        assertEquals(36, sessionCart.calculateOrderlinePrice(product,2));
    }

    @Test
    void calculateTotalPrice() {
        sessionCart.addOrderline(3, 2);
        sessionCart.addOrderline(7, 1);
        assertEquals(48, sessionCart.calculateTotalPrice());
    }

    @Test
    void size() {
        assertEquals(0, sessionCart.size());
        sessionCart.addOrderline(7, 1);
        assertEquals(1, sessionCart.size());
        sessionCart.addOrderline(3, 1);
        assertEquals(2, sessionCart.size());
    }

    @Test
    void clear() {
        sessionCart.addOrderline(7, 2);
        sessionCart.addOrderline(3, 5);
        sessionCart.clear();
        assertEquals(0, sessionCart.size());
    }

}

class MockProductRepository implements ProductRepository {
    private final Map<Integer, Product> productData = new HashMap<>();

    public MockProductRepository() {
        productData.put(3, new Product(3, "sample1", 15, 10));
        productData.put(7, new Product(7, "sample2", 18, 10));
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(productData.get(id));
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Product getOne(Integer integer) {
        return null;
    }

    @Override
    public Product getById(Integer integer) {
        return null;
    }

    @Override
    public Product getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Product> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public List<Product> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Product> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }
}
