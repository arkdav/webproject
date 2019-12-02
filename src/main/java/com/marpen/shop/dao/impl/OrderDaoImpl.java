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
    public List<Order> getOrdersByUserLogin(String userLogin) {
        String sql = "select * from orders where user_login like :user_login order by date desc";
        List<Order> orders = currentSession().createSQLQuery(sql).addEntity(Order.class).setParameter("user_login", userLogin).list();
        return orders;
    }
}
