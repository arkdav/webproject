package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderBundleDao;
import com.marpen.shop.model.OrderBundle;
import org.hibernate.Session;

import java.util.List;


public class OrderBundleDaoImpl extends GenericDaoImpl<OrderBundle> implements OrderBundleDao {

    private OrderBundleDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<OrderBundle> getOrderBundlesByUserLogin(String userLogin){
        String sql = "select * from orderbundle where user_login like :user_login order by date desc";
        List<OrderBundle> orderBundles = currentSession().createSQLQuery(sql).addEntity(OrderBundle.class).setParameter("user_login", userLogin).list();
        return orderBundles;
    }

}
