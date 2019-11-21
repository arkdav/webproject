package com.marpen.shop.dao;

import com.marpen.shop.model.Price;

public interface PriceDao extends GenericDao<Price>{

    Price getPriceByProductId(int productId);
}
