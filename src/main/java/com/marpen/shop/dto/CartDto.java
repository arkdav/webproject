package com.marpen.shop.dto;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartDto {

    private String userLogin;
    private int cartId;
    private String date;
    private List<CartProductDto> products;
    private Double cartPrice;

    public CartDto() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public void setDate(Date date) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        this.date = formatForDate.format(date);
    }

    public List<CartProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductDto> products) {
        this.products = products;
    }

    public Double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Double cartPrice) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        String formattedDouble = new DecimalFormat("##0.00").format(cartPrice);
        this.cartPrice = Double.valueOf(formattedDouble);
    }
}
