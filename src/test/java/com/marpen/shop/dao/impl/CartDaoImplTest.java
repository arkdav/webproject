package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CartDao;
import com.marpen.shop.model.Cart;
import com.marpen.shop.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/test-dao-persistance-context.xml"})
@Transactional
public class CartDaoImplTest {

    private static final String LOGIN_FIRST = "natali";
    private static final String LOGIN_SECOND = "kolya";
    private static final String LOGIN_NO_SUCH = "abra";
    private static final int CART_ID_FIRST = 1;
    private static final int CART_ID_SECOND = 2;
    private static final Date DATE_FIRST = new GregorianCalendar(2019, Calendar.NOVEMBER, 5).getTime();
    private static final Date DATE_SECOND = new GregorianCalendar(2019, Calendar.DECEMBER, 6).getTime();
    private static final Double TOTAL_PRICE_FIRST = 13.0;
    private static final Double TOTAL_PRICE_SECOND  = 25.0;
    private static final Cart CART_FIRST  = new Cart(CART_ID_FIRST, LOGIN_FIRST, DATE_FIRST, TOTAL_PRICE_FIRST);
    private static final Cart CART_SECOND  = new Cart(CART_ID_SECOND, LOGIN_SECOND, DATE_SECOND, TOTAL_PRICE_SECOND);

    @Autowired
    private CartDao cartDao;

    @Test
    public void get() {
        Cart actual = cartDao.get(CART_ID_FIRST);
        Assert.assertEquals(CART_ID_FIRST, actual.getCartId());
        Assert.assertEquals(LOGIN_FIRST, actual.getUserLogin());
        Assert.assertEquals(TOTAL_PRICE_FIRST, actual.getTotalPrice());
        Assert.assertEquals(actual, CART_FIRST);
    }

    @Test
    public void getAll() {
        List<Cart> carts = new ArrayList<>();
        carts.add(CART_FIRST);
        carts.add(CART_SECOND);
        List <Cart> actual = cartDao.getAll();
        Assert.assertEquals(actual.get(0).getUserLogin(), carts.get(0).getUserLogin());
        Assert.assertEquals(actual.get(0).getCartId(), carts.get(0).getCartId());
        Assert.assertEquals(actual.get(0).getTotalPrice(), carts.get(0).getTotalPrice());
    }

    @Test
    public void getCartByUserLogin() {
        Cart actual = cartDao.getCartByUserLogin(LOGIN_FIRST);
        Assert.assertEquals(actual, CART_FIRST);
    }

    @Test
    public void getCartByUserLogin_Empty() {
        Cart actual = cartDao.getCartByUserLogin(LOGIN_NO_SUCH);
        Assert.assertNull(actual);
    }
}