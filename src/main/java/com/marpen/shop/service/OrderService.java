package com.marpen.shop.service;

import com.marpen.shop.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByUserLogin(String userLogin);

    void save(Order order);

    Order getOrder(int orderId);
}
