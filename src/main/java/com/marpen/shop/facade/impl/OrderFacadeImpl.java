package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.facade.CartFacade;
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
    private CartFacade cartFacade;


    public OrderFacadeImpl(OrderService orderService, OrderEntryService orderEntryService, CartFacade cartFacade) {
        this.orderService = orderService;
        this.orderEntryService = orderEntryService;
        this.cartFacade = cartFacade;
    }

    @Override
    public void addCartToOrder(int userId) {
        CartDto cartDto = cartFacade.getCartByUserId(userId);
        Order order = fromCartDtoToOrder(cartDto);
        List<OrderEntry> orderEntries = fromCartDtoToOrderEntries(cartDto, order.getOrderId());
        orderService.save(order);
        for (OrderEntry orderEntry :
                orderEntries) {
            orderEntryService.save(orderEntry);
        }
    }

    private Order fromCartDtoToOrder(CartDto cartDto) {
        Date dateNow = new Date();
        Order order = new Order();
        order.setUserId(cartDto.getUserId());
        order.setDate(dateNow);
        order.setTotalprice(Double.valueOf(cartDto.getCartPrice()));
        return order;
    }

    private List<OrderEntry> fromCartDtoToOrderEntries(CartDto cartDto, int orderId) {
        List<OrderEntry> orderEntries = new ArrayList<>();
        List<CartProductDto> cartProductDtos = cartDto.getProducts();
        for (CartProductDto cartProductDto :
                cartProductDtos) {
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setOrderId(orderId);
            orderEntry.setAmount(cartProductDto.getAmount());
            orderEntry.setProductId(cartProductDto.getProductDto().getProductId());
            orderEntries.add(orderEntry);
        }
        return orderEntries;
    }
}
