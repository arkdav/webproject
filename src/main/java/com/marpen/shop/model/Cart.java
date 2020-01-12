package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return cartId == cart.cartId &&
                Objects.equals(userLogin, cart.userLogin) &&
                Objects.equals(formatter.format(date), formatter.format(cart.date)) &&
                Objects.equals(totalPrice, cart.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, userLogin, date, totalPrice);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userLogin='" + userLogin + '\'' +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
