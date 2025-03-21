package com.boerma.dealvago.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @OneToMany(mappedBy = "orderDetail", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

}
