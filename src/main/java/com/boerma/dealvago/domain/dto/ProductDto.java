package com.boerma.dealvago.domain.dto;

import org.springframework.stereotype.Component;

@Component
public class ProductDto {
    private int id;
    private String name;
    private String category;
    private double unitPrice;
    private int stock;

    public ProductDto(){

    }

    public ProductDto(int id, String name, String category, double unitPrice, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }
    
    public ProductDto(int id, String name, double unitPrice, int stock) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

}
