package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderEntryDao;
import com.marpen.shop.dao.impl.OrderBundleDaoImpl;
import com.marpen.shop.model.OrderBundle;
import com.marpen.shop.model.OrderEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderBundleServiceImplTest {

    @Mock
    private OrderBundleDaoImpl orderBundleDao;

    @InjectMocks
    private OrderBundleServiceImpl orderBundleService;

    private OrderBundle orderBundle1, orderBundle2;

    @Before
    public void setUp() {
        Date orderDate= new Date();
        orderBundle1 = new OrderBundle(1,"marina", orderDate, 20.0,"note");
        orderBundle2 = new OrderBundle(2,"marina", orderDate, 20.0,"note");
    }

    @Test
    public void getOrderBundlesByUserLogin() {
        List<OrderBundle> orderBundleList=new ArrayList<>();
        orderBundleList.add(orderBundle1);
        orderBundleList.add(orderBundle2);
        Mockito.when(orderBundleDao.getOrderBundlesByUserLogin("marina")).thenReturn(orderBundleList);
        List<OrderBundle> actual = orderBundleService.getOrderBundlesByUserLogin("marina");
        assertEquals(orderBundleList, actual);
    }

    @Test
    public void getOrderBundleById() {
        Mockito.when(orderBundleDao.get(2)).thenReturn(orderBundle2);
        OrderBundle actual = orderBundleService.getOrderBundleById(2);
        assertEquals(orderBundle2, actual);
    }
}