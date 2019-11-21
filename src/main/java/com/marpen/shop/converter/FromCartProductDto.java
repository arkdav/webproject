package com.marpen.shop.converter;

import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.model.OrderEntry;
import org.springframework.core.convert.converter.Converter;

public class FromCartProductDto implements Converter<CartProductDto, OrderEntry> {

    @Override
    public OrderEntry convert(CartProductDto cartProductDto) {
        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setAmount(cartProductDto.getAmount());
        orderEntry.setProductId(cartProductDto.getProductDto().getProductId());
        return orderEntry;
    }
}
