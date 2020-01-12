package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderBundleDao;
import com.marpen.shop.model.OrderBundle;
import com.marpen.shop.service.OrderBundleService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderBundleServiceImpl implements OrderBundleService {

    private OrderBundleDao orderBundleDao;

    public OrderBundleServiceImpl(OrderBundleDao orderBundleDao){
        this.orderBundleDao=orderBundleDao;
    }

    @Override
    public List<OrderBundle> getOrderBundlesByUserLoginAndDate(Date dateFrom, Date dateTo, String userLogin, int page, int perPage) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromString = dateFormat.format(dateFrom);
        String dateToString = dateFormat.format(dateTo);
        return orderBundleDao.getOrderBundlesByUserLoginAndDate(dateFromString, dateToString, userLogin, page, perPage);
    }

    @Override
    public int save(OrderBundle orderBundle) {
        orderBundleDao.save(orderBundle);
        return orderBundle.getOrderBundleId();
    }

    @Override
    public int getAmountOfOrderBundlesByLoginAndDate(Date dateFrom, Date dateTo, String userLogin) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromString = dateFormat.format(dateFrom);
        String dateToString = dateFormat.format(dateTo);
        List<OrderBundle> orderBundles =orderBundleDao.getAllOrderBundlesByLoginAndDate(dateFromString, dateToString, userLogin);
        return orderBundles!=null ? orderBundles.size() : 0;
    }

    @Override
    public OrderBundle getOrderBundleById(int orderBundleId) {
        return  orderBundleDao.get(orderBundleId);
    }
}
