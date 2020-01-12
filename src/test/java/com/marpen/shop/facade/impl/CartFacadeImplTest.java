package com.marpen.shop.facade.impl;

import com.marpen.shop.model.Cart;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.service.impl.CartEntryServiceImpl;
import com.marpen.shop.service.impl.CartServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CartFacadeImplTest {

    @Mock
    private CartEntryServiceImpl cartEntryService;
    @Mock
    private CartServiceImpl cartService;

    @InjectMocks
    private CartFacadeImpl cartFacade;

    private Cart cart;
    private CartEntry cartEntry;
    private static final String USER_LOGIN = "marina";
    private static final String USER_LOGIN_NO_SUCH = "mir";
    private static final Double TOTAL_PRICE = 20.0;
    private static final Date CART_DATE = new GregorianCalendar(2003, Calendar.SEPTEMBER, 25).getTime();
    private static final int CART_ID = 1;
    private static final int CART_ENTRY_ID = 1;
    private static final int PRODUCT_ID = 1;
    private static final int AMOUNT = 5;

    @Before
    public void setUp() {
        cart = new Cart(CART_ID, USER_LOGIN, CART_DATE, TOTAL_PRICE);
        cartEntry = new CartEntry(CART_ENTRY_ID, CART_ID, PRODUCT_ID, AMOUNT);
    }

    @Test
    public void getCartByUserLogin() {
        Cart actual = cartService.getCartByUserLogin(USER_LOGIN_NO_SUCH);
        assertNull(actual);
    }

    @Test
    public void getCartProductsAmount() {
        List<CartEntry> cartEntries = new ArrayList<>();
        cartEntries.add(cartEntry);
        Mockito.when(cartService.getCartByUserLogin(USER_LOGIN)).thenReturn(cart);
        Mockito.when(cartEntryService.getCartEntriesByCartId(CART_ID)).thenReturn(cartEntries);
        int actual = cartFacade.getCartProductsAmount(USER_LOGIN);
        assertEquals(cartEntries.size(), actual);
    }
}