package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
    public Price getPriceByProductId(int product_id){
            return  (Price) currentSession().load(Price.class, product_id);
    }

}
