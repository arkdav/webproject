package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderBundleDao;
import com.marpen.shop.model.OrderBundle;
import com.marpen.shop.service.OrderBundleService;

import java.util.List;

public class OrderBundleServiceImpl implements OrderBundleService {

    private OrderBundleDao orderBundleDao;

    public OrderBundleServiceImpl(OrderBundleDao orderBundleDao){
        this.orderBundleDao=orderBundleDao;
    }


    @Override
    public List<OrderBundle> getOrderBundlesByUserLogin(String userLogin) {
        return orderBundleDao.getOrderBundlesByUserLogin(userLogin);
    }

    @Override
    public int save(OrderBundle orderBundle) {
        orderBundleDao.save(orderBundle);
        return orderBundle.getOrderBundleId();
    }

    @Override
    public OrderBundle getOrderBundleById(int orderBundleId) {
        return  orderBundleDao.get(orderBundleId);
    }
}
