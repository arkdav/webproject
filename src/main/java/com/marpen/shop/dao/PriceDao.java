package com.marpen.shop.dao;

import com.marpen.shop.model.Price;

public interface PriceDao {

    Price getPriceByProductId(int productId);

}
