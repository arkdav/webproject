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
    public List<Order> getOrdersByOrderBundleId(int orderBundleId) {
        return orderDao.getOrdersByOrderBundleId(orderBundleId);
    }

    @Override
    public int save(Order order) {
        orderDao.save(order);
        return order.getOrderId();
    }

    @Override
    public int save(int orderBundleId, String ownerLogin, double totalPrice){
        Order order = new Order();
        order.setOrderBundleId(orderBundleId);
        order.setOwnerLogin(ownerLogin);
        order.setPrice(totalPrice);
        order.setStatusId(1);
        orderDao.save(order);
        return order.getOrderId();
    }

    @Override
    public Order getOrder(int orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public void changeOrderStatus(Order order) {
        if(order.getStatusId()==1){
            order.setStatusId(2);
        } else {
            order.setStatusId(1);
        }
        orderDao.update(order);
    }

    @Override
    public List<Order> getOrdersByOwnerLogin(String ownerLogin){
        return orderDao.getOrdersByOwnerLogin(ownerLogin);
    }

}
