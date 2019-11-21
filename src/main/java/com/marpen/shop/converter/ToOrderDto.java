package com.marpen.shop.converter;

import com.marpen.shop.dto.OrderDto;
import com.marpen.shop.dto.OrderProductDto;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.service.OrderEntryService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ToOrderDto implements Converter<Order, OrderDto> {

    private OrderEntryService orderEntryService;
    private ToOrderProductDto toOrderProductDto;

    public ToOrderDto(OrderEntryService orderEntryService, ToOrderProductDto toOrderProductDto) {
        this.orderEntryService = orderEntryService;
        this.toOrderProductDto = toOrderProductDto;
    }

    @Override
    public OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUserId());
        orderDto.setDate(order.getDate());
        orderDto.setOrderPrice(order.getTotalprice());
        List<OrderEntry> orderEntries = orderEntryService.getOrderEntriesByOrderId(order.getOrderId());
        List<OrderProductDto> products = new ArrayList<>();
        if (!orderEntries.isEmpty()) {
            for (OrderEntry orderEntry :
                    orderEntries) {
                OrderProductDto orderProductDto = toOrderProductDto.convert(orderEntry);
                products.add(orderProductDto);
            }
        }
        orderDto.setProducts(products);
        return orderDto;
    }
}
