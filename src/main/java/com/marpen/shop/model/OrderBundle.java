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
@Table(name="orderbundle")
@Proxy(lazy = false)
public class OrderBundle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderbundle_id")
    private int orderBundleId;

    @Column(name="user_login")
    private String userLogin;

    @Column(name = "date")
    private Date date;

    @Column(name = "totalprice")
    private Double totalPrice;

    @Column(name="ordernote")
    private String orderNote;

    public OrderBundle() {
    }

    public OrderBundle(int orderBundleId, String userLogin, Date date, Double totalPrice, String orderNote) {
        this.orderBundleId=orderBundleId;
        this.userLogin = userLogin;
        this.date = date;
        this.totalPrice = totalPrice;
        this.orderNote = orderNote;
    }

    public int getOrderBundleId() {
        return orderBundleId;
    }

    public void setOrderBundleId(int orderBundleId) {
        this.orderBundleId = orderBundleId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalprice) {
        this.totalPrice = totalprice;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }
}
