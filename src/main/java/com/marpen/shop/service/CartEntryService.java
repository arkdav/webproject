package com.marpen.shop.service;

import com.marpen.shop.model.CartEntry;

import java.util.List;

public interface CartEntryService {

    List<CartEntry> getCartEntriesByCartId(int cart_id);

    void save(CartEntry cartEntry);

    void save(int cart_id, int product_id);

    void save(int cart_id, int product_id, int amount);
}
