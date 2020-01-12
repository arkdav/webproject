package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.model.Price;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/test-dao-persistance-context.xml"})
@Transactional
public class PriceDaoImplTest {

    @Autowired
    private PriceDao priceDao;

    private static final int PRODUCT_ID = 1;
    private static final Double PRICE_AMOUNT = 5.0;
    private static final Price PRICE = new Price(PRODUCT_ID, PRICE_AMOUNT);

    @Test
    public void getPriceByProductId() {
        Price actual = priceDao.get(PRODUCT_ID);
        Assert.assertEquals(PRICE, actual);
    }
}