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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Orderline> orderlines = new ArrayList<>();

    public OrderDetail() {

    }

    public OrderDetail(User customer, LocalDate orderDate, String orderStatus) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderlines=" + orderlines +
                '}';
    }
}
