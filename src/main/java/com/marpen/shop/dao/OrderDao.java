package com.marpen.shop.dao;

import com.marpen.shop.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getOrdersByUserId(int userId);

    Order  getOrderByOrderId(int orderId);

    void save(Order order);
}
