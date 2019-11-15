package com.marpen.shop.dao;

import com.marpen.shop.model.OrderEntry;

import java.util.List;

public interface OrderEntryDao {

    List<OrderEntry> getOrderEntriesByOrderId(int orderId);

    OrderEntry getOrderEntryByProductId(int orderId, int productId);

    void save(OrderEntry orderEntry);

}
