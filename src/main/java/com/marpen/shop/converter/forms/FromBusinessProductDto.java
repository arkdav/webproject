package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.CatalogVersionService;
import com.marpen.shop.service.ProductService;
import org.springframework.core.convert.converter.Converter;

public class FromBusinessProductDto implements Converter<BusinessProductDto, Product> {

    private ProductService productService;
    private CatalogVersionService catalogVersionService;

    public FromBusinessProductDto(ProductService productService, CatalogVersionService catalogVersionService) {
        this.productService = productService;
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    public Product convert(BusinessProductDto businessProductDto) {
        Product product = productService.getProductById(businessProductDto.getProductId());
        if (businessProductDto.getName() != null && !businessProductDto.getName().isEmpty()) {
            product.setName(businessProductDto.getName());
        }
        if (businessProductDto.getDescription() != null && !businessProductDto.getDescription().isEmpty()) {
            product.setDescription(businessProductDto.getDescription());
        }
        if (businessProductDto.getCatalogVersion() != null && !businessProductDto.getCatalogVersion().isEmpty()) {
            product.setCatverId(catalogVersionService.getCatalogVersionIdByName(businessProductDto.getCatalogVersion()));
        }
        return product;
    }
}
