package com.marpen.shop.facade;

import com.marpen.shop.dto.CartDto;

public interface CartFacade {

    CartDto getCartByUserLogin(String userLogin);

    void addProductToCart(String userLogin, int productId, int productAmount);

    void updateProductInCart(String userLogin, int productId, int productAmount);

    void removeProductFromCart(String userLogin, int productId);

    void removeCart(String userLogin);

    int getCartProductsAmount(String userLogin);

    boolean productIsInUserCart(String userLogin, int productId);
}
