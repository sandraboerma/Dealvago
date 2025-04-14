package com.boerma.dealvago.service;

import com.boerma.dealvago.domain.dto.OrderlineDto;
import com.boerma.dealvago.domain.dto.ProductDto;
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
        productRepository.findById(productId).ifPresentOrElse(product -> {
                    ProductDto productDto = new ProductDto(
                            product.getId(),
                            product.getName(),
                            product.getUnitPrice(),
                            product.getStock()
                    );

                    if (modifyOrderline(productId, quantity, productDto)) {
                        return;
                    }

                    int totalPrice = calculateOrderlinePrice(productDto, quantity);
                    OrderlineDto newLine = new OrderlineDto(productDto, quantity, totalPrice);
                    cart.add(newLine);
                }, () -> {
                    System.out.println("Product not found");
                }
        );
    }

    public void updateQuantity(int productId, int newQuantity) {
        for (OrderlineDto orderline : cart) {
            if (orderline.getProductDto().getId() == productId) {
                if (newQuantity <= 0) {
                    removeOrderline(productId);
                } else {
                    orderline.setQuantity(newQuantity);
                    orderline.setTotalPrice(calculateOrderlinePrice(orderline.getProductDto(), newQuantity));
                }
            }
        }
    }

    public void removeOrderline(int productId) {
        cart.removeIf(orderline -> orderline.getProductDto().getId() == productId);
    }


    public int calculateOrderlinePrice(ProductDto product, int quantity) {
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

    private boolean modifyOrderline(int productId, int quantity, ProductDto productDto) {
        for (OrderlineDto existingItem : cart) {
            if (existingItem.getProductDto().getId() == productId) {
                int updateQuantity = existingItem.getQuantity() + quantity;
                existingItem.setQuantity(updateQuantity);
                existingItem.setTotalPrice(calculateOrderlinePrice(productDto, updateQuantity));
                return true;
            }
        }
        return false;
    }

}
