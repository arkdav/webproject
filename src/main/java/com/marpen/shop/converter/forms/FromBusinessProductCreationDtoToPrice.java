package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.model.Price;
import org.springframework.core.convert.converter.Converter;

public class FromBusinessProductCreationDtoToPrice implements Converter<BusinessProductCreationDto, Price> {

    @Override
    public Price convert(BusinessProductCreationDto businessProductCreationDto) {
        Price price = new Price();
        price.setPrice(Double.parseDouble(businessProductCreationDto.getPrice()));
        return price;
    }
}