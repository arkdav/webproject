package com.marpen.shop.converter;

import com.marpen.shop.dto.OrderProductDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.ProductService;
import org.springframework.core.convert.converter.Converter;

public class ToOrderProductDto implements Converter<OrderEntry, OrderProductDto> {

    private ProductService productService;
    private ToProductDto toProductDto;

    public ToOrderProductDto(ProductService productService, ToProductDto toProductDto) {
        this.productService = productService;
        this.toProductDto = toProductDto;
    }

    public OrderProductDto convert(OrderEntry orderEntry) {
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setAmount(orderEntry.getAmount());
        Product product = productService.getProductById(orderEntry.getProductId());
        ProductDto productDto = toProductDto.convert(product);
        orderProductDto.setProductDto(productDto);
        return orderProductDto;
    }

}
