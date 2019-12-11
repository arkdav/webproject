package com.marpen.shop.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BusinessOrderDto {
    private int orderBundleId;
    private int orderId;
    private List<BusinessOrderProductDto> businessOrderProductDto;
    private UserDto userDto;
    private String date;
    private String orderNote;
    private Double price;
    private String orderStatus;

    public BusinessOrderDto(){}

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        this.date = formatForDate.format(date);
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<BusinessOrderProductDto> getBusinessOrderProductDto() {
        return businessOrderProductDto;
    }

    public void setBusinessOrderProductDto(List<BusinessOrderProductDto> businessOrderProductDto) {
        this.businessOrderProductDto = businessOrderProductDto;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatusId) {
        this.orderStatus = orderStatusId;
    }

    public int getOrderBundleId() {
        return orderBundleId;
    }

    public void setOrderBundleId(int orderBundleId) {
        this.orderBundleId = orderBundleId;
    }
}
