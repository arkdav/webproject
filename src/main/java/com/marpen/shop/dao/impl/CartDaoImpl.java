package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CartDao;
import com.marpen.shop.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CartDaoImpl extends GenericDaoImpl<Cart> implements CartDao {

    private Session session;

    public CartDaoImpl(SessionFactory sessionFactory) {
        if (super.getSessionFactory() == null) {
            super.setSessionFactory(sessionFactory);

        }
        this.session = super.getSession();
    }

    private Session currentSession() {
        return this.session;
    }

    @Override
    public Cart get(int userId) {
        String sql = "select * from cart where user_id like :user_id";
        List<Cart> carts = currentSession().createSQLQuery(sql).addEntity(Cart.class).setParameter("user_id", userId).list();
        return carts.isEmpty() ? null : carts.get(0);
    }

}
