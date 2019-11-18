package com.marpen.shop.facade;

import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    List<ProductDto> getCatalogList(String searchName, int pageId, int productsPerPage);

    ProductDto getProductById(int productId);

    List<PageDto> getIdList(String searchName, int productsPerPage);

    List<BusinessProductDto> getProductsList();

}
