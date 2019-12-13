package com.marpen.shop.service.impl;

import com.marpen.shop.dao.impl.CartDaoImpl;
import com.marpen.shop.model.Cart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @Mock
    private CartDaoImpl cartDao;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart1;

    @Before
    public void setUp() {
        Date date = new Date();
        cart1 = new Cart(1, "marina", date, 20.0);
    }

    @Test
    public void getCartByUserLogin() {
        Mockito.when(cartDao.getCartByUserLogin("marina")).thenReturn(cart1);
        Cart actual = cartService.getCartByUserLogin("marina");
        assertEquals(cart1, actual);
    }
}