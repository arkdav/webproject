package com.marpen.shop.converter;

import com.marpen.shop.dto.OrderDto;
import com.marpen.shop.dto.OrderProductDto;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderBundle;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.service.OrderEntryService;
import com.marpen.shop.service.OrderService;
import com.marpen.shop.service.OrderStatusService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ToOrderDto implements Converter<OrderBundle, List<OrderDto>> {

    private OrderEntryService orderEntryService;
    private OrderStatusService orderStatusService;
    private OrderService orderService;
    private ToOrderProductDto toOrderProductDto;

    public ToOrderDto(OrderEntryService orderEntryService, OrderService orderService,
                      OrderStatusService orderStatusService, ToOrderProductDto toOrderProductDto) {
        this.orderEntryService = orderEntryService;
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.toOrderProductDto = toOrderProductDto;
    }

    @Override
    public List<OrderDto> convert(OrderBundle orderBundle) {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderService.getOrdersByOrderBundleId(orderBundle.getOrderBundleId());
        for (Order order :
                orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(orderBundle.getOrderBundleId());
            orderDto.setUserLogin(orderBundle.getUserLogin());
            orderDto.setDate(orderBundle.getDate());
            orderDto.setOrderPrice(order.getPrice());
            orderDto.setOrderNote(orderBundle.getOrderNote());
            String orderStatus = orderStatusService.getOrderStatusNameById(order.getStatusId());
            orderDto.setStatus(orderStatus);
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
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
