package com.marpen.shop.service.impl;

import com.marpen.shop.dao.OrderEntryDao;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.service.OrderEntryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderEntryServiceImpl implements OrderEntryService {

    private OrderEntryDao orderEntryDao;

    public OrderEntryServiceImpl(OrderEntryDao orderEntryDao){
        this.orderEntryDao=orderEntryDao;
    }


    @Override
    public List<OrderEntry> getOrderEntriesByOrderId(int orderId) {
        return orderEntryDao.getOrderEntriesByOrderId(orderId);
    }

    @Override
    public OrderEntry getOrderEntryByProductId(int orderId, int productId) {
        return orderEntryDao.getOrderEntryByProductId(orderId, productId);
    }

    @Override
    public void save(OrderEntry orderEntry) {
        orderEntryDao.save(orderEntry);
    }
}
