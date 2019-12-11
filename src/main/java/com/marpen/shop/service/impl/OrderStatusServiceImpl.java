package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderStatusDao;
import com.marpen.shop.model.OrderStatus;
import com.marpen.shop.service.OrderStatusService;

public class OrderStatusServiceImpl implements OrderStatusService {

    private OrderStatusDao orderStatusDao;

    public OrderStatusServiceImpl(OrderStatusDao orderStatusDao){
        this.orderStatusDao=orderStatusDao;
    }

    @Override
    public String getOrderStatusNameById(int orderStatusId) {
        OrderStatus orderStatus=orderStatusDao.get(orderStatusId);
        return orderStatus.getStatusName();
    }

    @Override
    public int getOrderStatusIdByName(String orderStatusName) {
        OrderStatus orderStatus=orderStatusDao.getOrderStatusByName(orderStatusName);
        return orderStatus==null ? 0 : orderStatus.getStatusId();
    }
}
