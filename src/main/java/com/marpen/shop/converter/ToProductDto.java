package com.marpen.shop.converter;

import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.PriceService;
import org.springframework.core.convert.converter.Converter;

public class ToProductDto implements Converter<Product, ProductDto> {

    private PriceService priceService;

    public ToProductDto(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageLink(product.getImageLink());
        productDto.setPrice(Double.toString(priceService.getPriceByProductId(product.getProductId()).getPrice()));
        return productDto;
    }
}
