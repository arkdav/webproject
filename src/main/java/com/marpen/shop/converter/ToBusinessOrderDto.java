package com.marpen.shop.converter;

import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.BusinessOrderProductDto;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderBundle;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.model.User;
import com.marpen.shop.service.OrderBundleService;
import com.marpen.shop.service.OrderEntryService;
import com.marpen.shop.service.OrderStatusService;
import com.marpen.shop.service.UserService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ToBusinessOrderDto implements Converter<Order, BusinessOrderDto> {

    private OrderEntryService orderEntryService;
    private OrderBundleService orderBundleService;
    private OrderStatusService orderStatusService;
    private UserService userService;
    private ToUserDto toUserDto;
    private ToBusinessOrderProductDto toBusinessOrderProductDto;

    public ToBusinessOrderDto(OrderEntryService orderEntryService,
                              OrderBundleService orderBundleService,
                              OrderStatusService orderStatusService,
                              UserService userService,
                              ToUserDto toUserDto,
                              ToBusinessOrderProductDto toBusinessOrderProductDto) {
        this.orderEntryService = orderEntryService;
        this.orderBundleService = orderBundleService;
        this.orderStatusService = orderStatusService;
        this.userService = userService;
        this.toUserDto = toUserDto;
        this.toBusinessOrderProductDto = toBusinessOrderProductDto;
    }

    @Override
    public BusinessOrderDto convert(Order order) {
        BusinessOrderDto businessOrderDto = new BusinessOrderDto();
        businessOrderDto.setOrderBundleId(order.getOrderBundleId());
        businessOrderDto.setOrderId(order.getOrderId());
        OrderBundle orderBundle = orderBundleService.getOrderBundleById(order.getOrderBundleId());
        User user = userService.getUserByLogin(orderBundle.getUserLogin());
        businessOrderDto.setUserDto(toUserDto.convert(user));
        businessOrderDto.setOrderNote(orderBundle.getOrderNote());
        businessOrderDto.setDate(orderBundle.getDate());
        String orderName = orderStatusService.getOrderStatusNameById(order.getStatusId());
        businessOrderDto.setOrderStatus(orderName);
        businessOrderDto.setPrice(order.getPrice());
        List<BusinessOrderProductDto> businessOrderProductDtos = new ArrayList<>();
        List<OrderEntry> orderEntries = orderEntryService.getOrderEntriesByOrderId(order.getOrderId());
        for (OrderEntry orderEntry :
                orderEntries) {
            businessOrderProductDtos.add(toBusinessOrderProductDto.convert(orderEntry));
        }
        businessOrderDto.setBusinessOrderProductDto(businessOrderProductDtos);
        return businessOrderDto;
    }
}
