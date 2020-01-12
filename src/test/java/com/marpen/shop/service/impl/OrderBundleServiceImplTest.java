package com.marpen.shop.service.impl;

import com.marpen.shop.dao.impl.OrderBundleDaoImpl;
import com.marpen.shop.model.OrderBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderBundleServiceImplTest {

    @Mock
    private OrderBundleDaoImpl orderBundleDao;

    @InjectMocks
    private OrderBundleServiceImpl orderBundleService;

    private OrderBundle orderBundle1, orderBundle2;
    private static final Date ORDER_DATE = new GregorianCalendar(2003, Calendar.SEPTEMBER, 25).getTime();
    private static final String USER_LOGIN = "marina";
    private static final Double TOTAL_PRICE = 20.0;
    private static final String ORDER_NOTE = "note";
    private static final int PAGE = 1;
    private static final int PER_PAGE = 3;

    @Before
    public void setUp() {
        orderBundle1 = new OrderBundle(1,USER_LOGIN, ORDER_DATE, TOTAL_PRICE,ORDER_NOTE);
        orderBundle2 = new OrderBundle(2,USER_LOGIN, ORDER_DATE, TOTAL_PRICE,ORDER_NOTE);
    }

    @Test
    public void getOrderBundlesByUserLoginAndDate() {
        List<OrderBundle> orderBundleList=new ArrayList<>();
        orderBundleList.add(orderBundle1);
        orderBundleList.add(orderBundle2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = formatter.format(ORDER_DATE);
        Mockito.when(orderBundleDao.getOrderBundlesByUserLoginAndDate(dateFormat, dateFormat, USER_LOGIN, PAGE, PER_PAGE)).thenReturn(orderBundleList);
        List<OrderBundle> actual = orderBundleService.getOrderBundlesByUserLoginAndDate(ORDER_DATE, ORDER_DATE, USER_LOGIN, PAGE, PER_PAGE);
        assertEquals(orderBundleList, actual);
    }

    @Test
    public void getOrderBundleById() {
        Mockito.when(orderBundleDao.get(2)).thenReturn(orderBundle2);
        OrderBundle actual = orderBundleService.getOrderBundleById(2);
        assertEquals(orderBundle2, actual);
    }

}