package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List <Order> getOrdersByUserId(int userId) {
        String sql = "select * from orders where user_id like :user_id";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("user_id", userId).list();
        return orders;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        Order order= (Order) currentSession().load(Order.class, orderId);
        return order;
    }

    @Override
    public void save(Order order) {
        currentSession().save(order);
    }
}
