package com.marpen.shop.converter;

import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.model.Product;
import com.marpen.shop.model.User;
import com.marpen.shop.service.OrderService;
import com.marpen.shop.service.PriceService;
import com.marpen.shop.service.ProductService;
import com.marpen.shop.service.UserService;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToBusinessOrderDto implements Converter<OrderEntry, BusinessOrderDto> {

    private OrderService orderService;
    private ProductService productService;
    private PriceService priceService;
    private UserService userService;
    private ToProductDto toProductDto;
    private ToUserDto toUserDto;

    public ToBusinessOrderDto(OrderService orderService, ProductService productService, PriceService priceService,
                              UserService userService, ToProductDto toProductDto, ToUserDto toUserDto) {
        this.orderService = orderService;
        this.productService=productService;
        this.priceService=priceService;
        this.userService=userService;
        this.toProductDto = toProductDto;
        this.toUserDto=toUserDto;
    }

    @Override
    public BusinessOrderDto convert(OrderEntry orderEntry) {
        BusinessOrderDto businessOrderDto=new BusinessOrderDto();
        Order order=orderService.getOrder( orderEntry.getOrderId());
        businessOrderDto.setAmount(orderEntry.getAmount());
        Date orderDate = order.getDate();
        businessOrderDto.setDate(new SimpleDateFormat("dd.MM.yyyy").format(orderDate));
        businessOrderDto.setOrderEntryId(orderEntry.getOrderEntryId());
        businessOrderDto.setOrderNote(order.getOrderNote());
        Product product=productService.getProductById(orderEntry.getProductId());
        businessOrderDto.setProductDto(toProductDto.convert(product));
        User user =userService.getUserByLogin(order.getUserLogin());
        businessOrderDto.setUserDto(toUserDto.convert(user));
        double price = priceService.getPriceByProductId(product.getProductId()).getPrice();
        businessOrderDto.setPrice(price*orderEntry.getAmount());
        return businessOrderDto;
    }
}
