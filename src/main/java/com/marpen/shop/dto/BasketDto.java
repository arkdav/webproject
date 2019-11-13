package com.marpen.shop.dto;

import java.util.Date;
import java.util.List;

public class BasketDto {

    private int userId;
    private int basketId;
    private Date date;
    private List<BasketProductDto> products;
    private double basketPrice;

    public BasketDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<BasketProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<BasketProductDto> products) {
        this.products = products;
    }

    public double getBasketPrice() {
        return basketPrice;
    }

    public void setBasketPrice(double basketPrice) {
        this.basketPrice = basketPrice;
    }
}
