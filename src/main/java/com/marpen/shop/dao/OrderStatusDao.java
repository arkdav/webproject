package com.marpen.shop.dao;

import com.marpen.shop.model.OrderStatus;

public interface OrderStatusDao extends GenericDao<OrderStatus> {

    OrderStatus getOrderStatusByName(String orderStatusName);
}
