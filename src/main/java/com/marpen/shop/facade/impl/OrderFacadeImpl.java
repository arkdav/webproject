package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.FromCartDto;
import com.marpen.shop.converter.FromCartProductDto;
import com.marpen.shop.converter.ToBusinessOrderDto;
import com.marpen.shop.converter.ToOrderDto;
import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.dto.OrderDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.facade.ProductFacade;
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
    private ProductFacade productFacade;
    private ToOrderDto toOrderDto;
    private FromCartDto fromCartDto;
    private FromCartProductDto fromCartProductDto;
    private ToBusinessOrderDto toBusinessOrderDto;


    public OrderFacadeImpl(OrderService orderService, OrderEntryService orderEntryService, CartFacade cartFacade,
                           ProductFacade productFacade, ToOrderDto toOrderDto, FromCartDto fromCartDto,
                           FromCartProductDto fromCartProductDto, ToBusinessOrderDto toBusinessOrderDto) {
        this.orderService = orderService;
        this.orderEntryService = orderEntryService;
        this.cartFacade = cartFacade;
        this.productFacade=productFacade;
        this.toOrderDto = toOrderDto;
        this.fromCartDto = fromCartDto;
        this.fromCartProductDto = fromCartProductDto;
        this.toBusinessOrderDto = toBusinessOrderDto;
    }

    @Override
    public void addCartToOrder(String userLogin, String orderNote) {
        CartDto cartDto = cartFacade.getCartByUserLogin(userLogin);
        Order order = fromCartDto.convert(cartDto);
        order.setOrderNote(orderNote);
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
    public List<OrderDto> getOrdersByUserLogin(String userLogin) {
        List<Order> orders = orderService.getOrdersByUserLogin(userLogin);
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

    @Override
    public List<BusinessOrderDto> getBusinessOrders(String login) {
        List<BusinessProductDto> products= productFacade.getProductsListByUserLogin(login);
        List<BusinessOrderDto> businessOrderDtos = new ArrayList<>();
        List<OrderEntry> entries = orderEntryService.getOrderEntries();
        for (OrderEntry orderEntry :
                entries) {
            for (BusinessProductDto businessProductDto :
                    products) {
                if (orderEntry.getProductId() == businessProductDto.getProductId()) {
                    BusinessOrderDto businessOrderDto = toBusinessOrderDto.convert(orderEntry);
                    businessOrderDtos.add(businessOrderDto);
                }
            }
        }
        return businessOrderDtos;
    }
}
