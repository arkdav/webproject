package com.marpen.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartentry")
public class CartEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cartentry_id")
    private int cartEntryId;

    @Column(name="cart_id")
    private int cartId;

    @Column(name="product_id")
    private int productId;

    @Column(name="amount")
    private int amount;

    public CartEntry() {
    }

    public int getCartEntryId() {
        return cartEntryId;
    }

    public void setCartEntryId(int cartEntryId) {
        this.cartEntryId = cartEntryId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

