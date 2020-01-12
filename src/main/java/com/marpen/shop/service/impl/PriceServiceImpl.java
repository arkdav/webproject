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
    public Price getPriceByProductId(int productId){
        return this.priceDao.get(productId);
    }

    @Override
    public void savePrice(Price price){
        priceDao.save(price);
    }

    @Override
    public void updatePrice(Price price){
        priceDao.update(price);
    }


}
