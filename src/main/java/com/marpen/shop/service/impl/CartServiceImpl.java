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
    public Cart getCartByUserId(int userId) {
        return cartDao.get(userId);
    }

    @Override
    public void save(Cart cart) {
        cartDao.save(cart);
    }

    @Override
    public void save(int userId) {
        Cart cart = new Cart();
        Date dateNow = new Date();
        cart.setDate(dateNow);
        cart.setUserId(userId);
        cart.setTotalPrice(0.00);
        cartDao.save(cart);
    }

    @Override
    public void removeCart(int userId) {
        Cart cart = cartDao.get(userId);
        cartDao.delete(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartDao.update(cart);
    }
}
