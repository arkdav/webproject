package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="orders")
@Proxy(lazy = false)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;

    @Column(name="price")
    private double price;

    @Column(name="status_id")
    private int statusId;

    @Column(name="orderbundle_id")
    private int orderBundleId;

    @Column(name = "owner_login")
    private String ownerLogin;

    public Order() {
    }

    public Order(int orderId, double price, int statusId, int orderBundleId, String ownerLogin) {
        this.orderId=orderId;
        this.price = price;
        this.statusId = statusId;
        this.orderBundleId = orderBundleId;
        this.ownerLogin = ownerLogin;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getOrderBundleId() {
        return orderBundleId;
    }

    public void setOrderBundleId(int orderBundleId) {
        this.orderBundleId = orderBundleId;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Double.compare(order.price, price) == 0 &&
                statusId == order.statusId &&
                orderBundleId == order.orderBundleId &&
                Objects.equals(ownerLogin, order.ownerLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, price, statusId, orderBundleId, ownerLogin);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", price=" + price +
                ", statusId=" + statusId +
                ", orderBundleId=" + orderBundleId +
                ", ownerLogin='" + ownerLogin + '\'' +
                '}';
    }
}
