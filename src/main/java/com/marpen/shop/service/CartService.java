package com.marpen.shop.service;

import com.marpen.shop.model.Cart;

public interface CartService {

    Cart getCartByUserId(int userId);

    int getCartIdByUserId(int userId);

    void save(Cart cart);

    void save(int userId);

    void removeCart(int userId);

    void updateCart(Cart cart);
}
