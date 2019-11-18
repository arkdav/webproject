package com.marpen.shop.dto;

public class OrderProductDto {

    private ProductDto productDto;
    private int amount;

    public OrderProductDto() {
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
