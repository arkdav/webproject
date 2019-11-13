package com.marpen.shop.facade;

import com.marpen.shop.dto.BasketDto;

public interface BasketFacade {

    BasketDto getBasketByUserId(int userId);

    void addProductToBasket(int userId, int productId);
}
