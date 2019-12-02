package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import org.hibernate.Session;

public class PriceDaoImpl extends GenericDaoImpl<Price> implements PriceDao {

    private PriceDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }


    @Override
    public Price getPriceByProductId(int productId) {
        return (Price) currentSession().load(Price.class, productId);
    }
}
