package com.boerma.dealvago.domain.dto;

public class OrderlineDto {
    private ProductDto productDto;
    private int quantity;
    private int totalPrice;

    public OrderlineDto(ProductDto productDto, int quantity, int totalPrice) {
        this.productDto = productDto;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderlineDto() {
    }

    public ProductDto getProductDto() {
        return productDto;
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

