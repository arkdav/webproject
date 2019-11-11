package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CartEntryDao;
import com.marpen.shop.model.CartEntry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CartEntryDaoImpl implements CartEntryDao {
    private SessionFactory sessionFactory;

    public CartEntryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<CartEntry> getCartEntriesByCartId(int cart_id) {
        String sql="select * from cartentry where cart_id like :cart_id";
        List<CartEntry> cartEntries=currentSession().createSQLQuery(sql).addEntity(CartEntry.class).setParameter("cart_id", cart_id).list();
        return cartEntries;
    }

    @Override
    public void save(CartEntry cartEntry) {
        currentSession().save(cartEntry);
    }
}
