package com.boerma.dealvago.domain.dto;

public class OrderlineDto {
    private ProductDto product;
    private int quantity;
    private int totalPrice;

    public OrderlineDto(ProductDto product, int quantity, int totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderlineDto() {
    }

    public ProductDto getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}

