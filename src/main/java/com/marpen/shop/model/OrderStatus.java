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
@Table(name="orderstatus")
@Proxy(lazy = false)
public class OrderStatus {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "status_id")
        private int statusId;

        @Column(name = "statusname")
        private String statusName;

    public OrderStatus() {
    }

    public OrderStatus(int statusId, String statusName) {
        this.statusId=statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return statusId == that.statusId &&
                Objects.equals(statusName, that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusName);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
