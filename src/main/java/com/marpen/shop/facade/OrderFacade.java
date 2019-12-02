package com.marpen.shop.facade;

import com.marpen.shop.dto.OrderDto;

import java.util.List;

public interface OrderFacade {

    void addCartToOrder(String userLogin);

    List<OrderDto> getOrdersByUserLogin(String userLogin);

}
