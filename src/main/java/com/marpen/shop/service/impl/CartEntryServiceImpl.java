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
    public List <CartEntry> getCartEntriesByCartId(int cartId) {
        return cartEntryDao.getCartEntriesByCartId(cartId);
    }

    @Override
    public CartEntry getCartEntryByProductId(int cartId, int productId){
        return cartEntryDao.getCartEntryByProductId(cartId, productId);
    }

    @Override
    public void save(CartEntry cartEntry) {
        cartEntryDao.save(cartEntry);
    }

    @Override
    public void save(int cartId, int productId, int amount) {
        CartEntry cartEntry =new CartEntry();
        cartEntry.setCartId(cartId);
        cartEntry.setProductId(productId);
        cartEntry.setAmount(amount);
        cartEntryDao.save(cartEntry);
    }

    @Override
    public void save(int cartId, int productId) {
        CartEntry cartEntry =new CartEntry();
        cartEntry.setCartId(cartId);
        cartEntry.setProductId(productId);
        cartEntry.setAmount(1);
        cartEntryDao.save(cartEntry);
    }

    @Override
    public void updateCartEntry(CartEntry cartEntry){
        cartEntryDao.update(cartEntry);
    }

    @Override
    public void removeCartEntry(CartEntry cartEntry){
        cartEntryDao.remove(cartEntry);
    }

    @Override
    public void removeCartEntries(int cartId) {
        List<CartEntry> cartEntries=cartEntryDao.getCartEntriesByCartId(cartId);
        for (CartEntry cartEntry:
             cartEntries) {
            cartEntryDao.remove(cartEntry);
        }
    }
}
