package com.boerma.dealvago.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderlines")
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    public Orderline() {

    }

    public Orderline(OrderDetail orderDetail, Product product, int quantity, int productPrice) {
        this.orderDetail = orderDetail;
        this.product = product;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Orderline{" +
                "id=" + id +
                ", orderDetail=" + orderDetail +
                ", product=" + product +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
