package com.marpen.shop.service.impl;

import com.marpen.shop.dao.impl.CartEntryDaoImpl;
import com.marpen.shop.model.CartEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CartEntryServiceImplTest {

    @Mock
    private CartEntryDaoImpl cartEntryDao;

    @InjectMocks
    private CartEntryServiceImpl cartEntryService;

    private CartEntry cartEntry1, cartEntry2, cartEntry3;

    @Before
    public void setUp() {
        cartEntry1 = new CartEntry(1, 1, 1, 3);
        cartEntry2 = new CartEntry(2, 1, 4, 1);
        cartEntry3 = new CartEntry(3, 2, 1, 1);
    }

    @Test
    public void getCartEntriesByCartId() {
        List<CartEntry> cartEntryList = new ArrayList<>();
        cartEntryList.add(cartEntry1);
        cartEntryList.add(cartEntry2);
        Mockito.when(cartEntryDao.getCartEntriesByCartId(1)).thenReturn(cartEntryList);
        List<CartEntry> actualOrderEntryList = cartEntryService.getCartEntriesByCartId(1);
        assertEquals(cartEntryList, actualOrderEntryList);
    }

    @Test
    public void getCartEntryByProductId() {
        Mockito.when(cartEntryDao.getCartEntryByProductId(2, 1)).thenReturn(cartEntry3);
        CartEntry actualCartEntry = cartEntryService.getCartEntryByProductId(2, 1);
        assertEquals(cartEntry3, actualCartEntry);
    }
}