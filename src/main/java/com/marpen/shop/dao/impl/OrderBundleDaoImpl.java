package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.OrderBundleDao;
import com.marpen.shop.model.OrderBundle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class OrderBundleDaoImpl extends GenericDaoImpl<OrderBundle> implements OrderBundleDao {

    public OrderBundleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<OrderBundle> getOrderBundlesByUserLoginAndDate(String dateFrom, String dateTo, String userLogin, int page, int perPage){
        String sql = "select * from orderbundle where user_login like :user_login and date >= '"+dateFrom+ "' and date <= '"+dateTo+"' order by date desc limit "+(page-1)+","+perPage;
        List<OrderBundle> orderBundles = currentSession().createSQLQuery(sql).addEntity(OrderBundle.class).setParameter("user_login", userLogin).list();
        return orderBundles;
    }

    @Override
    public List<OrderBundle> getAllOrderBundlesByLoginAndDate(String dateFrom, String dateTo, String userLogin) {
        String sql = "select * from orderbundle where user_login like :user_login and date >= '"+dateFrom+ "' and date <= '"+dateTo+"'";
        List<OrderBundle> orderBundles = currentSession().createSQLQuery(sql).addEntity(OrderBundle.class).setParameter("user_login", userLogin).list();
        return orderBundles;
    }

}
