package com.marpen.shop.service.impl;

import com.marpen.shop.dao.CartDao;
import com.marpen.shop.model.Cart;
import com.marpen.shop.service.CartService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Transactional
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    public CartServiceImpl(CartDao cartDao){
        this.cartDao=cartDao;
    }

    @Override
    public Cart getCartByUserId(int user_id){
        return cartDao.getCartByUserId(user_id);
    }

    @Override
    public int getCartIdByUserId(int user_id){
        return cartDao.getCartByUserId(user_id).getCart_id();
    }

    @Override
    public void save(Cart cart){
        cartDao.save(cart);
    }

    @Override
    public void save(int userId){
        Cart cart=new Cart();
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cart.setDate(dateNow);
        cart.setUser_id(userId);
        cartDao.save(cart);
    }
}
