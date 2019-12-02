package com.marpen.shop.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private String userLogin;
    private int orderId;
    private String date;
    private List<OrderProductDto> products;
    private Double orderPrice;

    public OrderDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        this.date = formatForDate.format(date);
    }

    public List<OrderProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDto> products) {
        this.products = products;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        //String formattedDouble = String.format("%.2f", orderPrice);
        this.orderPrice = orderPrice;
    }
}
