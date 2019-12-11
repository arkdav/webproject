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

    List<BusinessProductDto> getProductsListByUserLogin(String userLogin);

    List<Integer> getProductIdsListByUserLogin(String userLogin);

    BusinessProductDto getBusinessProductDtoById(int productId);

    void deleteProduct(int productId);

    void updateProduct(String userLogin, BusinessProductDto businessProductDto);

    void createProduct(String userLogin, BusinessProductCreationDto businessProductCreationDto);

}
