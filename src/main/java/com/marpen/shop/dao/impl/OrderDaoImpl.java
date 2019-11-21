package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private Session session;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        if (super.getSessionFactory() == null) {
            super.setSessionFactory(sessionFactory);

        }
        this.session = super.getSession();
    }

    private Session currentSession() {
        return this.session;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        String sql = "select * from orders where user_id like :user_id order by date desc";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("user_id", userId).list();
        return orders;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        Order order = (Order) currentSession().load(Order.class, orderId);
        return order;
    }

}
