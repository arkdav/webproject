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
    @Column(name="entry_id")
    private int entry_id;

    @Column(name="cart_id")
    private int cart_id;

    @Column(name="product_id")
    private int product_id;

    @Column(name="amount")
    private int amount;

    public CartEntry() {
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

