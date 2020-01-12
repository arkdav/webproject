package com.marpen.shop.service;

import com.marpen.shop.model.OrderBundle;

import java.util.Date;
import java.util.List;

public interface OrderBundleService {

    List<OrderBundle> getOrderBundlesByUserLoginAndDate(Date dateFrom, Date dateTo, String userLogin, int page, int perPage);

    int save(OrderBundle orderBundle);

    int getAmountOfOrderBundlesByLoginAndDate(Date dateFrom, Date dateTo, String userLogin);

    OrderBundle getOrderBundleById (int orderBundleId);
}
