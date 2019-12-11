package com.marpen.shop.dto;

public class BusinessOrderProductDto {

    private int orderEntryId;
    private int amount;
    private ProductDto productDto;
    private double price;

    public BusinessOrderProductDto(){}


    public int getOrderEntryId() {
        return orderEntryId;
    }

    public void setOrderEntryId(int orderEntryId) {
        this.orderEntryId = orderEntryId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
