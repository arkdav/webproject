package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PriceDaoImpl implements PriceDao {

    private SessionFactory sessionFactory;

    public PriceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Price getPriceByProductId(int productId){
        return  (Price) currentSession().load(Price.class, productId);
    }

}
