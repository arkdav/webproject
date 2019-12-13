package com.marpen.shop.service.impl;

import com.marpen.shop.dao.impl.CatalogVersionDaoImpl;
import com.marpen.shop.dao.impl.OrderStatusDaoImpl;
import com.marpen.shop.model.CatalogVersion;
import com.marpen.shop.model.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderStatusServiceImplTest {

    @Mock
    private OrderStatusDaoImpl orderStatusDao;

    @InjectMocks
    private OrderStatusServiceImpl orderStatusService;

    private OrderStatus orderStatus1;

    @Before
    public void setUp() {
        orderStatus1 = new OrderStatus(1, "collecting");
    }

    @Test
    public void getOrderStatusNameById() {
        Mockito.when(orderStatusDao.get(1)).thenReturn(orderStatus1);
        String actual = orderStatusService.getOrderStatusNameById(1);
        assertEquals(orderStatus1.getStatusName(), actual);
    }

    @Test
    public void getOrderStatusIdByName() {
        Mockito.when(orderStatusDao.getOrderStatusByName("collecting")).thenReturn(orderStatus1);
        int actual = orderStatusService.getOrderStatusIdByName("collecting");
        assertEquals(orderStatus1.getStatusId(), actual);
    }
}