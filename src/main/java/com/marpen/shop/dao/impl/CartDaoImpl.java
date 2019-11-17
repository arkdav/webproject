package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CartDao;
import com.marpen.shop.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CartDaoImpl implements CartDao {

    private SessionFactory sessionFactory;

    public CartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Cart getCartByUserId(int userId) {
        String sql = "select * from cart where user_id like :user_id";
        List<Cart> carts = currentSession().createSQLQuery(sql).addEntity(Cart.class).setParameter("user_id", userId).list();
        return carts.isEmpty() ? null : carts.get(0);
    }

    @Override
    public void save(Cart cart) {
        currentSession().save(cart);
    }

    @Override
    public void remove(Cart cart){currentSession().delete(cart);}

    @Override
    public void update(Cart cart) {
        currentSession().update(cart);
    }

}
