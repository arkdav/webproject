package com.marpen.shop.service;

import com.marpen.shop.model.Cart;

public interface CartService {

    Cart getCartByUserId(int user_id);

    int getCartIdByUserId(int user_id);

    void save(Cart cart);

    void save(int userId);
}
