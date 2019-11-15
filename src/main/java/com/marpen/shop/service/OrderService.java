package com.marpen.shop.service;

import com.marpen.shop.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByUserId(int userId);

    Order  getOrderByOrderId(int orderId);

    void save(Order order);

}
