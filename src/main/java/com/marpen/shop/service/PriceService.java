package com.marpen.shop.service;

import com.marpen.shop.model.Price;

public interface PriceService {

    Price getPriceByProductId(int productId);

    void savePrice(Price price);

    void updatePrice(Price price);

}
