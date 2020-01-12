package com.marpen.shop.facade;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    List<ProductDto> getCatalogList(String searchName, int pageId, int productsPerPage, String sort);

    ProductDto getProductById(int productId);

    List<PageDto> getCatalogPagesList(String searchName, int productsPerPage);

    List<BusinessProductDto> getBusinessProductsList(String catVer, String searchName, int pageId, int productsPerPage, String userLogin);

    List<PageDto> getBusinessPagesList(String catVer, String searchName, int productsPerPage, String userLogin);

    BusinessProductDto getBusinessProductDtoById(int productId);

    boolean deleteProducts(String [] productIds);

    boolean changeProductsVersion(String [] productIds);

    void updateProduct(String userLogin, BusinessProductDto businessProductDto);

    void createProduct(String userLogin, BusinessProductCreationDto businessProductCreationDto);

}
