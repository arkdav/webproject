package com.marpen.shop.dao;

import com.marpen.shop.model.Cart;

public interface CartDao extends GenericDao<Cart> {

    Cart getCartByUserLogin (String userLogin);
}
