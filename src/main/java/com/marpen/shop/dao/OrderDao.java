package com.marpen.shop.dao;

import com.marpen.shop.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> getOrdersByOrderBundleId(int orderBundleId);

    List<Order> getOrdersByOwnerLogin(String ownerLogin, int pageId, int ordersPerPage);

    List<Order> getOrdersByOwnerLoginAndStatusId(int statusId, String ownerLogin, int pageId, int ordersPerPage);

    List<Order> getAllOrdersByOwnerLogin(String ownerLogin);

    List<Order> getAllOrdersByOwnerLoginAndStatusId(int statusId, String ownerLogin);
}
