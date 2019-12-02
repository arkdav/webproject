package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CartDao;
import com.marpen.shop.model.Cart;
import org.hibernate.Session;

import java.util.List;

public class CartDaoImpl extends GenericDaoImpl<Cart> implements CartDao {

    private CartDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public Cart getCartByUserLogin(String userLogin) {
        String sql = "select * from cart where user_login like :user_login";
        List<Cart> carts = currentSession().createSQLQuery(sql).addEntity(Cart.class).setParameter("user_login", userLogin).list();
        return carts.isEmpty() ? null : carts.get(0);
    }

}
