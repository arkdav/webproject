package com.marpen.shop.facade;

import com.marpen.shop.dto.CartDto;

public interface CartFacade {

    CartDto getCartByUserId(int userId);

    void addProductToCart(int userId, int productId);

    void removeProductFromCart(int userId, int productId);

    void removeCart(int userId);
}
