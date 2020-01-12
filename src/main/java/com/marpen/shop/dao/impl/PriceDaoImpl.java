package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PriceDaoImpl extends GenericDaoImpl<Price> implements PriceDao {

    public PriceDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Session currentSession() {
        return super.getSession();
    }
}
