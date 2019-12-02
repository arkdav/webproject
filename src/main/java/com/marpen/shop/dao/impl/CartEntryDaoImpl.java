package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CartEntryDao;
import com.marpen.shop.model.CartEntry;
import org.hibernate.Session;

import java.util.List;

public class CartEntryDaoImpl extends GenericDaoImpl<CartEntry> implements CartEntryDao {

    private CartEntryDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<CartEntry> getCartEntriesByCartId(int cartId) {
        String sql = "select * from cartentry where cart_id like :cart_id";
        List<CartEntry> cartEntries = currentSession().createSQLQuery(sql).addEntity(CartEntry.class).setParameter("cart_id", cartId).list();
        return cartEntries;
    }

    @Override
    public CartEntry getCartEntryByProductId(int cartId, int productId) {
        String sql = "select * from cartentry where cart_id like :cart_id and product_id like :product_id";
        List<CartEntry> cartEntries = currentSession().createSQLQuery(sql).addEntity(CartEntry.class).setParameter("cart_id", cartId).setParameter("product_id", productId).list();
        return cartEntries.isEmpty() ? null : cartEntries.get(0);
    }

}
