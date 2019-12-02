package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.model.Order;
import com.marpen.shop.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao){
        this.orderDao=orderDao;
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        return orderDao.getOrdersByUserLogin(login);
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }
}
