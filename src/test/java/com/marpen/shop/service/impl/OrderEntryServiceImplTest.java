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

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderEntryServiceImplTest {

    @Mock
    private OrderEntryDao orderEntryDao;

    @InjectMocks
    private OrderEntryServiceImpl orderEntryService;

    private OrderEntry orderEntry1, orderEntry2, orderEntry3;

    @Before
    public void setUp() {
        orderEntry1 = new OrderEntry(1,2,3,2);
        orderEntry2= new OrderEntry(2,2,4,1);
        orderEntry3= new OrderEntry(3,1,5,1);
    }

    @Test
    public void getOrderEntries() {
        List<OrderEntry> orderEntryList=new ArrayList<>();
        orderEntryList.add(orderEntry1);
        orderEntryList.add(orderEntry2);
        orderEntryList.add(orderEntry3);
        Mockito.when(orderEntryDao.getOrderEntries()).thenReturn(orderEntryList);
        List<OrderEntry> actual = orderEntryService.getOrderEntries();
        assertEquals(orderEntryList, actual);
    }

    @Test
    public void getOrderEntriesByOrderId() {
        List<OrderEntry> orderEntryList=new ArrayList<>();
        orderEntryList.add(orderEntry1);
        orderEntryList.add(orderEntry2);
        orderEntryList.add(orderEntry3);
        Mockito.when(orderEntryDao.getOrderEntriesByOrderId(2)).thenReturn(orderEntryList);
        List<OrderEntry> actualOrderEntryList= orderEntryService.getOrderEntriesByOrderId(2);
        assertEquals(orderEntryList, actualOrderEntryList);
    }

}