package com.marpen.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderentry")
public class OrderEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderentry_id")
    private int orderEntryId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "amount")
    private int amount;

    public OrderEntry() {
    }

    public OrderEntry(int orderEntryId, int orderId, int productId, int amount) {
        this.orderEntryId=orderEntryId;
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }

    public int getOrderEntryId() {
        return orderEntryId;
    }

    public void setEntryEntryId(int orderEntryId) {
        this.orderEntryId = orderEntryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
