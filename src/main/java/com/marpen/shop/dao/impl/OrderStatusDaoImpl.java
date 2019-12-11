package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderStatusDao;
import com.marpen.shop.model.CatalogVersion;
import com.marpen.shop.model.OrderStatus;
import org.hibernate.Session;

import java.util.List;

public class OrderStatusDaoImpl extends GenericDaoImpl<OrderStatus> implements OrderStatusDao {

    private OrderStatusDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public OrderStatus getOrderStatusByName(String orderStatusName) {
        String sql = "Select * from orderstatus where statusname like :statusname";
        List<OrderStatus> orderStatuses = currentSession().createSQLQuery(sql).addEntity(CatalogVersion.class).setParameter("statusname", orderStatusName).list();
        return orderStatuses.isEmpty() ? null : orderStatuses.get(0);
    }
}
