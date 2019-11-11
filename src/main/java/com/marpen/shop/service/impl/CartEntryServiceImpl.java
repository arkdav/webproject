package com.marpen.shop.service.impl;

import com.marpen.shop.dao.CartEntryDao;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.service.CartEntryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CartEntryServiceImpl implements CartEntryService {
    private CartEntryDao cartEntryDao;

    public CartEntryServiceImpl(CartEntryDao cartEntryDao){
        this.cartEntryDao=cartEntryDao;
    }


    @Override
    public List <CartEntry> getCartEntriesByCartId(int cart_id) {
        return cartEntryDao.getCartEntriesByCartId(cart_id);
    }

    @Override
    public void save(CartEntry cartEntry) {
        cartEntryDao.save(cartEntry);
    }

    @Override
    public void save(int cart_id, int product_id, int amount) {
        CartEntry cartEntry =new CartEntry();
        cartEntry.setCart_id(cart_id);
        cartEntry.setProduct_id(product_id);
        cartEntry.setAmount(amount);
        cartEntryDao.save(cartEntry);
    }

    @Override
    public void save(int cart_id, int product_id) {
        CartEntry cartEntry =new CartEntry();
        cartEntry.setCart_id(cart_id);
        cartEntry.setProduct_id(product_id);
        cartEntry.setAmount(1);
        cartEntryDao.save(cartEntry);
    }
}
