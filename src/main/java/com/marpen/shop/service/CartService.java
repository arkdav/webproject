package com.marpen.shop.service;

import com.marpen.shop.model.Cart;

public interface CartService {

    Cart getCartByUserLogin(String userLogin);

    void save(String userLogin);

    void removeCart(String userLogin);

    void updateCart(Cart cart);
}
