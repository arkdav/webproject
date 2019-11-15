package com.marpen.shop.dao;

import com.marpen.shop.model.CartEntry;

import java.util.List;

public interface CartEntryDao {

    List<CartEntry> getCartEntriesByCartId(int cartId);

    CartEntry getCartEntryByProductId(int cartId, int productId);

    void save(CartEntry cartEntry);

    void update(CartEntry cartEntry);

    void remove(CartEntry cartEntry);

}
