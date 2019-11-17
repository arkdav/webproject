package com.marpen.shop.dao;

import com.marpen.shop.model.Cart;

public interface CartDao {

    Cart getCartByUserId(int userId);

    void save(Cart cart);

    void remove(Cart cart);

    void update(Cart cart);
}
