package com.marpen.shop.service;

import com.marpen.shop.model.CartEntry;

import java.util.List;

public interface CartEntryService {

    List<CartEntry> getCartEntriesByCartId(int cartId);

    CartEntry getCartEntryByProductId(int cartId, int productId);

    void save(CartEntry cartEntry);

    void save(int cartId, int productId);

    void save(int cartId, int productId, int amount);

    void updateCartEntry(CartEntry cartEntry);

    void removeCartEntry(CartEntry cartEntry);

    void removeCartEntries(int cartId);
}
