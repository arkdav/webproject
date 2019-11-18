package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.*;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.facade.ProductFacade;
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
    private ProductFacade productFacade;


    public OrderFacadeImpl(OrderService orderService, OrderEntryService orderEntryService, CartFacade cartFacade, ProductFacade productFacade) {
        this.orderService = orderService;
        this.orderEntryService = orderEntryService;
        this.cartFacade = cartFacade;
        this.productFacade=productFacade;
    }

    @Override
    public void addCartToOrder(int userId) {
        CartDto cartDto = cartFacade.getCartByUserId(userId);
        Order order = fromCartDtoToOrder(cartDto);
        orderService.save(order);
        List<OrderEntry> orderEntries = fromCartDtoToOrderEntries(cartDto, orderService.getOrderId(order));
        for (OrderEntry orderEntry :
                orderEntries) {
            orderEntryService.save(orderEntry);
        }
    }

    @Override
    public List<OrderDto> getOrdersByUserId(int userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        List<OrderDto> orderDtos =new ArrayList<>();
        if (!orders.isEmpty()) {
            for (Order order :
                    orders) {
                List<OrderEntry> orderEntries= orderEntryService.getOrderEntriesByOrderId(order.getOrderId());
                OrderDto orderDto = fromOrderAndEntriesToOrderDto(order, orderEntries);
                orderDtos.add(orderDto);
            }
        }
        return orderDtos;
    }

    private Order fromCartDtoToOrder(CartDto cartDto) {
        Date dateNow = new Date();
        Order order = new Order();
        order.setUserId(cartDto.getUserId());
        order.setDate(dateNow);
        order.setTotalprice(cartDto.getCartPrice());
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

    private OrderDto fromOrderAndEntriesToOrderDto(Order order, List<OrderEntry> orderEntries) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUserId());
        orderDto.setDate(order.getDate());
        orderDto.setOrderPrice(order.getTotalprice());
        List<OrderProductDto> products = new ArrayList<>();
        if (!orderEntries.isEmpty()) {
            for (OrderEntry orderEntry :
                    orderEntries) {
                OrderProductDto orderProductDto =new OrderProductDto();
                orderProductDto.setAmount(orderEntry.getAmount());
                ProductDto productDto = productFacade.getProductById(orderEntry.getProductId());
                orderProductDto.setProductDto(productDto);
                products.add(orderProductDto);
            }
        }
        orderDto.setProducts(products);
        return orderDto;
    }
}
