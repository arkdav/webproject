package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.BasketDto;
import com.marpen.shop.dto.BasketProductDto;
import com.marpen.shop.facade.BasketFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.service.OrderEntryService;
import com.marpen.shop.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFacadeImpl implements OrderFacade {
    private OrderService orderService;
    private OrderEntryService orderEntryService;
    private BasketFacade basketFacade;


    public OrderFacadeImpl(OrderService orderService, OrderEntryService orderEntryService, BasketFacade basketFacade) {
        this.orderService = orderService;
        this.orderEntryService = orderEntryService;
        this.basketFacade = basketFacade;
    }

    @Override
    public void addBasketToOrder(int userId) {
        BasketDto basketDto = basketFacade.getBasketByUserId(userId);
        Order order = fromBasketDtoToOrder(basketDto);
        List<OrderEntry> orderEntries = fromBasketDtoToOrderEntries(basketDto);
        orderService.save(order);
        for (OrderEntry orderEntry :
                orderEntries) {
            orderEntryService.save(orderEntry);
        }
    }

    private Order fromBasketDtoToOrder(BasketDto basketDto) {
        Date dateNow = new Date();
        Order order = new Order();
        order.setUserId(basketDto.getUserId());
        order.setDate(dateNow);
        order.setTotalprice(basketDto.getBasketPrice());
        return order;
    }

    private List<OrderEntry> fromBasketDtoToOrderEntries(BasketDto basketDto) {
        List<OrderEntry> orderEntries = new ArrayList<>();
        List<BasketProductDto> basketProductDtos = basketDto.getProducts();
        for (BasketProductDto basketProductDto :
                basketProductDtos) {
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setCartId(basketDto.getBasketId());
            orderEntry.setAmount(basketProductDto.getAmount());
            orderEntry.setPrice(basketProductDto.getTotalPrice());
            orderEntry.setProductId(basketProductDto.getProductDto().getProductId());
            orderEntries.add(orderEntry);
        }
        return orderEntries;
    }
}
