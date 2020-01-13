package com.marpen.shop.converter;

import com.marpen.shop.dto.BusinessOrderProductDto;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.PriceService;
import com.marpen.shop.service.ProductService;
import org.springframework.core.convert.converter.Converter;

public class ToBusinessOrderProductDto implements Converter<OrderEntry, BusinessOrderProductDto> {

    private ProductService productService;
    private PriceService priceService;
    private ToProductDto toProductDto;

    public ToBusinessOrderProductDto(ProductService productService, PriceService priceService, ToProductDto toProductDto) {

        this.productService = productService;
        this.priceService = priceService;
        this.toProductDto = toProductDto;
    }

    @Override
    public BusinessOrderProductDto convert(OrderEntry orderEntry) {
        BusinessOrderProductDto businessOrderProductDto = new BusinessOrderProductDto();
        businessOrderProductDto.setAmount(orderEntry.getAmount());
        businessOrderProductDto.setOrderEntryId(orderEntry.getOrderEntryId());
        Product product = productService.getProductById(orderEntry.getProductId());
        businessOrderProductDto.setProductDto(toProductDto.convert(product));
        double price = priceService.getPriceByProductId(product.getProductId()).getPrice();
        businessOrderProductDto.setPrice(price * orderEntry.getAmount());
        return businessOrderProductDto;
    }
}
