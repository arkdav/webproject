package com.marpen.shop.service;

public interface OrderStatusService {

    String getOrderStatusNameById(int orderStatusId);

    int getOrderStatusIdByName(String orderStatusName);
}
