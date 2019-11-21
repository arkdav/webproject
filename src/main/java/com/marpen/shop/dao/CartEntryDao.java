package com.marpen.shop.dao;

import com.marpen.shop.model.CartEntry;

import java.util.List;

public interface CartEntryDao extends GenericDao<CartEntry> {

    List<CartEntry> getCartEntriesByCartId(int cartId);

    CartEntry getCartEntryByProductId(int cartId, int productId);
}
