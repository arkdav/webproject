package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="cart")
@Proxy(lazy = false)
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cart_id")
    private int cartId;

    @Column(name="user_login")
    private String userLogin;

    @Column(name="date")
    private Date date;

    @Column(name="totalprice")
    private Double totalPrice;

    public Cart() {
    }

    public Cart(int cartId, String userLogin, Date date, Double totalPrice) {
        this.cartId=cartId;
        this.userLogin = userLogin;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String login) {
        this.userLogin = login;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}
