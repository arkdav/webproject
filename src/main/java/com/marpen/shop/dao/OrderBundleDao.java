package com.marpen.shop.dao;

import com.marpen.shop.model.OrderBundle;

import java.util.Date;
import java.util.List;

public interface OrderBundleDao extends GenericDao<OrderBundle> {

    List<OrderBundle> getOrderBundlesByUserLoginAndDate(String dateFrom, String DateTo, String userLogin, int page, int perPage);

    List<OrderBundle> getAllOrderBundlesByLoginAndDate(String dateFrom, String dateTo, String userLogin);

}
