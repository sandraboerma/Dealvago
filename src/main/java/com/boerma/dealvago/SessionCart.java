package com.boerma.dealvago;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class SessionCart {

    ProductDAO productDAO;

    List<Product> content = new ArrayList();

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    boolean addProduct(int productId) {
        Optional<Product> orderline = productDAO.getById(productId);
        if (orderline.isPresent()) {
            content.add(orderline.get());
            return true;
        } else {
            return false;
        }
    }

    int addOrderline(int quantity) {
        return 0;
    }

    void modifyOrderline() {

    }

    void removeOrderline(int productId) {
        content.removeIf((p) -> p.getId() == productId);
    }

    void getOrderline() {

    }

    int calculateOrderlinePrice(Product product, int quantity) {
        return product.getPrice() * quantity;
    }

    int calculateTotalPrice() {
        int total = 0;
        for (Product p : content) {
            total += p.getPrice();
        }
        return total;
    }

    int size() {
        return content.size();
    }

    void clear() {
        content.clear();
    }

}
