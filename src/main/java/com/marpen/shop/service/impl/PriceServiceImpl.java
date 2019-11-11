package com.marpen.shop.service.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import com.marpen.shop.service.PriceService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PriceServiceImpl implements PriceService {

    private PriceDao priceDao;

    public PriceServiceImpl(PriceDao priceDao){
        this.priceDao=priceDao;
    }

    @Override
    public Price getPriceByProductId(int product_id){
        return this.priceDao.getPriceByProductId(product_id);
    }


}
