package com.marpen.shop.service;

import com.marpen.shop.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByOrderBundleId(int orderBundleId);

    int save(Order order);

    int save(int orderBundleId, String ownerLogin, double totalPrice);

    List<Order> getOrders(String status, String ownerLogin, int pageId, int ordersPerPage);

    Order getOrder(int orderId);

    void changeOrderStatus(Order order);

    int getBusinessOrdersAmount(String userLogin, String status);

}
