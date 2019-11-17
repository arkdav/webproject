package com.marpen.shop.dto;

import java.util.Date;
import java.util.List;

public class CartDto {

    private int userId;
    private int cartId;
    private String date;
    private List<CartProductDto> products;
    private String cartPrice;

    public CartDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CartProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductDto> products) {
        this.products = products;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }
}
