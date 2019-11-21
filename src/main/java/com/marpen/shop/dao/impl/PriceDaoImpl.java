package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PriceDaoImpl extends GenericDaoImpl<Price> implements PriceDao {

    private Session session;

    public PriceDaoImpl(SessionFactory sessionFactory) {
        if (super.getSessionFactory() == null) {
            super.setSessionFactory(sessionFactory);

        }
        this.session = super.getSession();
    }

    private Session currentSession() {
        return this.session;
    }

    @Override
    public Price getPriceByProductId(int productId) {
        return (Price) currentSession().load(Price.class, productId);
    }
}
