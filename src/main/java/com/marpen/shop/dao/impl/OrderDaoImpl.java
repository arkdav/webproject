package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
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
    public List<Order> getOrdersByOwnerLogin(String ownerLogin, int pageId, int ordersPerPage) {
        String sql = "select * from orders where owner_login like :owner_login limit "+(pageId - 1) + "," + ordersPerPage;
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("owner_login", ownerLogin).list();
        return orders;
    }

    @Override
    public List<Order> getOrdersByOwnerLoginAndStatusId(int statusId, String ownerLogin, int pageId, int ordersPerPage) {
        String sql = "select * from orders where owner_login like :owner_login and status_id like :status_id limit "+(pageId - 1) + "," + ordersPerPage;
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("owner_login", ownerLogin).setParameter("status_id", statusId).list();
        return orders;
    }

    @Override
    public List<Order> getAllOrdersByOwnerLogin(String ownerLogin) {
        String sql = "select * from orders where owner_login like :owner_login";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("owner_login", ownerLogin).list();
        return orders;
    }

    @Override
    public List<Order> getAllOrdersByOwnerLoginAndStatusId(int statusId, String ownerLogin) {
        String sql = "select * from orders where owner_login like :owner_login and status_id like :status_id";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("owner_login", ownerLogin).setParameter("status_id", statusId).list();
        return orders;
    }
}
