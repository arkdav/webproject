package com.marpen.shop.facade;

import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.OrderDto;
import com.marpen.shop.dto.PageDto;

import java.util.Date;
import java.util.List;

public interface OrderFacade {

    void addCartToOrder(String userLogin, String userText);

    List<OrderDto> getOrdersByUserLoginAndDate(String dateFrom, String dateTo, String userLogin, int pageId, int ordersPerPage);

    List<BusinessOrderDto> getBusinessOrders(String status, String login, int pageId, int ordersPerPage);

    void changeOrderStatus(int orderId);

    List<PageDto> getOrdersPagesList(String dateFrom, String dateTo, String userLogin, int ordersPerPage);

    List<PageDto> getBusinessPagesList(String status, String login, int productsPerPage);
}
