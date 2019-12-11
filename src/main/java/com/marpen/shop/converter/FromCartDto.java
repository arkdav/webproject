package com.marpen.shop.converter;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.model.OrderBundle;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class FromCartDto implements Converter<CartDto, OrderBundle> {

    @Override
    public OrderBundle convert(CartDto cartDto) {
        Date dateNow = new Date();
        OrderBundle orderBundle = new OrderBundle();
        orderBundle.setUserLogin(cartDto.getUserLogin());
        orderBundle.setDate(dateNow);
        orderBundle.setTotalPrice(cartDto.getCartPrice());
        return orderBundle;
    }
}
