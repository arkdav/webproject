package com.marpen.shop.converter;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.model.Order;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class FromCartDto implements Converter<CartDto, Order> {

    @Override
    public Order convert(CartDto cartDto) {
        Date dateNow = new Date();
        Order order = new Order();
        order.setUserId(cartDto.getUserId());
        order.setDate(dateNow);
        order.setTotalprice(cartDto.getCartPrice());
        return order;
    }
}
