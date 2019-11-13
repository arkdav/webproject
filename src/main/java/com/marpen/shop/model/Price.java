package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "price")
@Proxy(lazy = false)
public class Price {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "price")
    private double price;

    public Price() {
    }

    public Price(int productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    public Integer getProductId() {

        return productId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
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
                "productId=" + productId +
                ", price=" + price +
                '}';
    }
}
