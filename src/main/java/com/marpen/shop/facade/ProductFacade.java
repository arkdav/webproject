package com.marpen.shop.facade;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    List<ProductDto> getCatalogList(String searchName, int pageId, int productsPerPage);

    ProductDto getProductById(int productId);

    List<PageDto> getCatalogPagesList(String searchName, int productsPerPage);

    List<BusinessProductDto> getProductsListByUserId(int userId);

    BusinessProductDto getBusinessProductDtoById(int productId);

    void deleteProduct(int product_id);

    void updateProduct(BusinessProductDto businessProductDto);

    void createProduct(BusinessProductCreationDto businessProductCreationDto);

}
