package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.model.Price;
import com.marpen.shop.service.PriceService;
import org.springframework.core.convert.converter.Converter;

public class FromBusinessProductDtoToPrice implements Converter<BusinessProductDto, Price> {

    private PriceService priceService;

    public FromBusinessProductDtoToPrice(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public Price convert(BusinessProductDto businessProductDto) {
        Price price = priceService.getPriceByProductId(businessProductDto.getProductId());
        if (businessProductDto.getPrice() != null && !businessProductDto.getPrice().isEmpty()) {
            price.setPrice(Double.parseDouble(businessProductDto.getPrice()));
        }
        return price;
    }
}
