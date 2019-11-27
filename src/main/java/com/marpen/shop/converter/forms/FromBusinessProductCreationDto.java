package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.CatalogVersionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class FromBusinessProductCreationDto implements Converter<BusinessProductCreationDto, Product> {

    private CatalogVersionService catalogVersionService;

    public FromBusinessProductCreationDto(CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    public Product convert(BusinessProductCreationDto businessProductCreationDto) {
        Product product = new Product();
        product.setName(businessProductCreationDto.getName());
        product.setDescription(businessProductCreationDto.getDescription());
        MultipartFile file = businessProductCreationDto.getImage();
        product.setImageLink(file.getOriginalFilename());
        product.setCatverId(catalogVersionService.getCatalogVersionIdByName(businessProductCreationDto.getCatalogVersion()));
        product.setUserId(businessProductCreationDto.getUserId());
        return product;
    }
}
