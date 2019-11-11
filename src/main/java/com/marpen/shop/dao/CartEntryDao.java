package com.marpen.shop.dao;

import com.marpen.shop.model.CartEntry;

import java.util.List;

public interface CartEntryDao {

    List<CartEntry> getCartEntriesByCartId(int cart_id);

    void save(CartEntry cartEntry);
}
