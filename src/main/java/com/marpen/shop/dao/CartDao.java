package com.marpen.shop.dao;

import com.marpen.shop.model.Cart;

public interface CartDao {

    Cart getCartByUserId(int user_id);

    void save(Cart cart);
}
