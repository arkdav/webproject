package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "price")
@Proxy(lazy = false)
public class Price {

    @Id
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "price")
    private double price;

    public Price() {
    }

    public Price(int product_id, double price) {
        this.product_id = product_id;
        this.price = price;
    }

    public Integer getProduct_id() {

        return product_id;
    }

    public void setProduct_id(int product_id) {

        this.product_id = product_id;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "product_id=" + product_id +
                ", price=" + price +
                '}';
    }
}
