package com.boerma.dealvago.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderlines")
public class OrderLine {

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
    private double productPrice;

}
