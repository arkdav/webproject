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
    public void save(OrderEntry orderEntry) {
        orderEntryDao.save(orderEntry);
    }

    @Override
    public void save(int orderId, int productId, int amount){
        OrderEntry orderEntry=new OrderEntry();
        orderEntry.setOrderId(orderId);
        orderEntry.setProductId(productId);
        orderEntry.setAmount(amount);
        orderEntryDao.save(orderEntry);
    }

    @Override
    public List<OrderEntry> getOrderEntries() {
        return orderEntryDao.getOrderEntries();
    }
}
