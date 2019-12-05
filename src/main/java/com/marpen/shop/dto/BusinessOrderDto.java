package com.marpen.shop.dto;

public class BusinessOrderDto {
    private int orderEntryId;
    private int amount;
    private UserDto userDto;
    private String date;
    private String orderNote;
    private ProductDto productDto;
    private Double price;

    public BusinessOrderDto(){}

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
