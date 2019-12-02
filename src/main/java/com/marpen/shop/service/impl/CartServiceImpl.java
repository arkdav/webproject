package com.marpen.shop.service.impl;

import com.marpen.shop.dao.CartDao;
import com.marpen.shop.model.Cart;
import com.marpen.shop.service.CartService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public Cart getCartByUserLogin(String userLogin) {
        return cartDao.getCartByUserLogin(userLogin);
    }

    @Override
    public void save(String userLogin) {
        Cart cart = new Cart();
        Date dateNow = new Date();
        cart.setDate(dateNow);
        cart.setUserLogin(userLogin);
        cart.setTotalPrice(0.00);
        cartDao.save(cart);
    }

    @Override
    public void removeCart(String userLogin) {
        Cart cart = cartDao.getCartByUserLogin(userLogin);
        cartDao.delete(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartDao.update(cart);
    }
}
