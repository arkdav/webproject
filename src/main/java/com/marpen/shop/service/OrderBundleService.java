package com.marpen.shop.service;

import com.marpen.shop.model.OrderBundle;

import java.util.List;

public interface OrderBundleService {

    List<OrderBundle> getOrderBundlesByUserLogin(String userLogin);

    int save(OrderBundle orderBundle);

    OrderBundle getOrderBundleById (int orderBundleId);
}
