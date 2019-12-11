package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.model.Order;
import org.hibernate.Session;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private OrderDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<Order> getOrdersByOrderBundleId(int orderBundleId) {
        String sql = "select * from orders where orderbundle_id like :orderbundle_id";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("orderbundle_id", orderBundleId).list();
        return orders;
    }

    @Override
    public List<Order> getOrdersByOwnerLogin(String ownerLogin) {
        String sql = "select * from orders where owner_login like :owner_login";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("owner_login", ownerLogin).list();
        return orders;
    }
}
