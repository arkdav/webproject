package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderDao;
import com.marpen.shop.model.Order;
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
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order1, order2, order3;

    @Before
    public void setUp() {
        order1 = new Order(1, 20.0,1,1,"marina");
        order2= new Order(2, 30.0, 1, 1, "marina");
        order3= new Order(3,20.0,1, 2, "marina");
    }

    @Test
    public void getOrdersByOrderBundleId() {
        List<Order> orderEntryList=new ArrayList<>();
        orderEntryList.add(order1);
        orderEntryList.add(order2);
        Mockito.when(orderDao.getOrdersByOrderBundleId(1)).thenReturn(orderEntryList);
        List<Order> actual = orderService.getOrdersByOrderBundleId(1);
        assertEquals(orderEntryList, actual);
    }

    @Test
    public void getOrder() {
        Mockito.when(orderDao.get(3)).thenReturn(order3);
        Order actual = orderService.getOrder(3);
        assertEquals(order3, actual);
    }

    @Test
    public void getOrdersByOwnerLogin() {
        List<Order> orderEntryList=new ArrayList<>();
        orderEntryList.add(order1);
        orderEntryList.add(order2);
        orderEntryList.add(order3);
        Mockito.when(orderDao.getOrdersByOwnerLogin("marina")).thenReturn(orderEntryList);
        List<Order> actual = orderService.getOrdersByOwnerLogin("marina");
        assertEquals(orderEntryList, actual);
    }
}