package com.marpen.shop.dao;

import com.marpen.shop.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> getOrdersByUserId(int userId);
}
