package com.marpen.shop.facade;

import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.OrderDto;

import java.util.List;

public interface OrderFacade {

    void addCartToOrder(String userLogin, String userText);

    List<OrderDto> getOrdersByUserLogin(String userLogin);

    List<BusinessOrderDto> getBusinessOrders(String login);

    void changeOrderStatus(int orderId);
}
