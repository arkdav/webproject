package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.dao.OrderStatusDao;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderStatus;
import com.marpen.shop.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderStatusDao orderStatusDao;

    public OrderServiceImpl(OrderDao orderDao, OrderStatusDao orderStatusDao){
        this.orderDao=orderDao;
        this.orderStatusDao=orderStatusDao;
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
        String statusName=orderStatusDao.get(order.getStatusId()).getStatusName();
        if (statusName.equals("processing")){
            order.setStatusId(orderStatusDao.getOrderStatusByName("collected").getStatusId());
            orderDao.update(order);
        }
    }

    @Override
    public List<Order> getOrders(String status, String ownerLogin, int pageId, int ordersPerPage){
        List <Order> orders;
        if(status.isEmpty()){
            orders = orderDao.getOrdersByOwnerLogin(ownerLogin, pageId, ordersPerPage);
        } else {
            int orderStatusId = orderStatusDao.getOrderStatusByName(status).getStatusId();
            orders=orderDao.getOrdersByOwnerLoginAndStatusId(orderStatusId, ownerLogin, pageId, ordersPerPage);
        }
       return orders;
    }

    @Override
    public int getBusinessOrdersAmount(String userLogin, String status){
        List<Order> orders;
        if(status.isEmpty()){
             orders = orderDao.getAllOrdersByOwnerLogin(userLogin);
        } else {
            int orderStatusId = orderStatusDao.getOrderStatusByName(status).getStatusId();
            orders = orderDao.getAllOrdersByOwnerLoginAndStatusId(orderStatusId, userLogin);
        }
        return orders!=null ? orders.size() : 0;
    }
}
