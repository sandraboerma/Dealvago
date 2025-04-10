package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import com.boerma.dealvago.domain.dto.ProductDto;
import com.boerma.dealvago.domain.entity.Product;
import com.boerma.dealvago.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class SessionCartService {

    private static final Logger logger = LoggerFactory.getLogger(SessionCartService.class);
    ProductRepository productRepository;
    List<OrderlineDto> cart = new ArrayList();

    @Autowired
    public SessionCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addOrderline(int productId, int quantity) {
        productRepository.findById(productId).ifPresentOrElse(
                product -> {
                    int totalPrice = calculateOrderlinePrice(product, quantity);
                    ProductDto productDto = new ProductDto(
                            product.getId(),
                            product.getName(),
                            product.getUnitPrice(),
                            product.getStock()
                    );
                    OrderlineDto newLine = new OrderlineDto(productDto, quantity, totalPrice);
                    cart.add(newLine);
                }, () -> {
                    System.out.println("Product not found");
                }
        );
    }

    public void modifyOrderline() {

    }

    public void removeOrderline(int productId) {
        cart.removeIf(orderLine -> orderLine.getProduct().getId() == productId);
    }


    public int calculateOrderlinePrice(Product product, int quantity) {
        return product.getUnitPrice() * quantity;
    }

    public int calculateTotalPrice() {
        int total = 0;
        for (OrderlineDto p : cart) {
            total += p.getTotalPrice();
        }
        return total;
    }

    public int size() {
        return cart.size();
    }

    public List<OrderlineDto> getOrderlines() {
        return new ArrayList<>(cart);
    }

    public void clear() {
        cart.clear();
    }

}
