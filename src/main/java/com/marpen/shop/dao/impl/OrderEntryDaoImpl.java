package com.marpen.shop.dao.impl;


import com.marpen.shop.dao.OrderEntryDao;
import com.marpen.shop.model.OrderEntry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderEntryDaoImpl implements OrderEntryDao {

    private SessionFactory sessionFactory;

    public OrderEntryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<OrderEntry> getOrderEntriesByOrderId(int orderId) {
        String sql = "select * from orderentry where order_id like :order_id";
        List<OrderEntry> orderEntries = currentSession().createSQLQuery(sql).addEntity(OrderEntry.class).setParameter("cart_id", orderId).list();
        return orderEntries;
    }

    @Override
    public OrderEntry getOrderEntryByProductId(int orderId, int productId) {
        String sql = "select * from orderentry where order_id like :order_id and product_id like :product_id";
        List<OrderEntry> orderEntries = currentSession().createSQLQuery(sql).addEntity(OrderEntry.class).setParameter("order_id", orderId).setParameter("product_id", productId).list();
        return orderEntries.isEmpty() ? null : orderEntries.get(0);
    }

    @Override
    public void save(OrderEntry orderEntry) {
        currentSession().save(orderEntry);
    }
}
