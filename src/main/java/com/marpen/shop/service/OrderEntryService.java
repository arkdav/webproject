package com.marpen.shop.service;

import com.marpen.shop.model.OrderEntry;

import java.util.List;

public interface OrderEntryService {

    List<OrderEntry> getOrderEntriesByOrderId(int orderId);

    OrderEntry getOrderEntryByProductId(int orderId, int productId);

    void save(OrderEntry orderEntry);

}
