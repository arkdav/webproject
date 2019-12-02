package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.CatalogVersionService;
import com.marpen.shop.service.PriceService;
import org.springframework.core.convert.converter.Converter;

public class ToBusinessProductDto implements Converter<Product, BusinessProductDto> {

    private PriceService priceService;
    private CatalogVersionService catalogVersionService;


    public ToBusinessProductDto(PriceService priceService, CatalogVersionService catalogVersionService) {
        this.priceService = priceService;
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    public BusinessProductDto convert(Product product) {
        BusinessProductDto businessProductDto = new BusinessProductDto();
        businessProductDto.setProductId(product.getProductId());
        businessProductDto.setName(product.getName());
        businessProductDto.setImageLink(product.getImageLink());
        businessProductDto.setDescription(product.getDescription());
        businessProductDto.setUserLogin(product.getUserLogin());
        businessProductDto.setPrice(Double.toString(priceService.getPriceByProductId(product.getProductId()).getPrice()));
        businessProductDto.setCatalogVersion(catalogVersionService.getCatalogVersionNameById(product.getCatverId()));
        return businessProductDto;
    }
}
