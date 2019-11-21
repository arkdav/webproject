package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.FromCartDto;
import com.marpen.shop.converter.FromCartProductDto;
import com.marpen.shop.converter.ToOrderDto;
import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.dto.OrderDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.service.OrderEntryService;
import com.marpen.shop.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderFacadeImpl implements OrderFacade {

    private OrderService orderService;
    private OrderEntryService orderEntryService;
    private CartFacade cartFacade;
    private ToOrderDto toOrderDto;
    private FromCartDto fromCartDto;
    private FromCartProductDto fromCartProductDto;


    public OrderFacadeImpl(OrderService orderService, OrderEntryService orderEntryService, CartFacade cartFacade,
                           ToOrderDto toOrderDto, FromCartDto fromCartDto, FromCartProductDto fromCartProductDto) {
        this.orderService = orderService;
        this.orderEntryService = orderEntryService;
        this.cartFacade = cartFacade;
        this.toOrderDto = toOrderDto;
        this.fromCartDto = fromCartDto;
        this.fromCartProductDto = fromCartProductDto;
    }

    @Override
    public void addCartToOrder(int userId) {
        CartDto cartDto = cartFacade.getCartByUserId(userId);
        Order order = fromCartDto.convert(cartDto);
        orderService.save(order);
        List<CartProductDto> cartProductDtos = cartDto.getProducts();
        if (!cartProductDtos.isEmpty()) {
            for (CartProductDto cartProductDto :
                    cartProductDtos) {
                OrderEntry orderEntry = fromCartProductDto.convert(cartProductDto);
                orderEntry.setOrderId(order.getOrderId());
                orderEntryService.save(orderEntry);
            }
        }
    }

    @Override
    public List<OrderDto> getOrdersByUserId(int userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        if (!orders.isEmpty()) {
            for (Order order :
                    orders) {
                OrderDto orderDto = toOrderDto.convert(order);
                orderDtos.add(orderDto);
            }
        }
        return orderDtos;
    }

}
