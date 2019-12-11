package com.marpen.shop.dao;

import com.marpen.shop.model.OrderBundle;

import java.util.List;

public interface OrderBundleDao extends GenericDao<OrderBundle> {

    List<OrderBundle> getOrderBundlesByUserLogin(String userLogin);
}
