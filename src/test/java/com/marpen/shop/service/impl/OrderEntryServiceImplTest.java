package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderEntryDao;
import com.marpen.shop.model.OrderEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderEntryServiceImplTest {

    @Mock
    private OrderEntryDao orderEntryDao;

    @InjectMocks
    private OrderEntryServiceImpl orderEntryService;

    private OrderEntry orderEntry, orderEntry2, orderEntry3;

    @Before
    public void setUp() {
        orderEntry = new OrderEntry(1,2,3,2);
        orderEntry2= new OrderEntry(2,2,4,1);
        orderEntry3= new OrderEntry(3,1,5,1);
    }

    @Test
    public void getOrderEntryByProductId() {
        Mockito.when(orderEntryDao.getOrderEntryByProductId(2,3)).thenReturn(orderEntry);
        OrderEntry actual = orderEntryDao.getOrderEntryByProductId(2,3);
        assertEquals(orderEntry, actual);
    }

    @Test
    public void getOrderEntriesByOrderId() {
        List<OrderEntry> orderEntryList=new ArrayList<>();
        orderEntryList.add(orderEntry);
        orderEntryList.add(orderEntry2);
        orderEntryList.add(orderEntry3);
        Mockito.when(orderEntryDao.getOrderEntriesByOrderId(2)).thenReturn(orderEntryList);
        List<OrderEntry> actualOrderEntryList= orderEntryDao.getOrderEntriesByOrderId(2);
        assertEquals(orderEntryList, actualOrderEntryList);
    }

}