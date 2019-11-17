package com.marpen.shop.facade;

import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    List<ProductDto> getCatalogList(int pageId, int productsPerPage);

    List<ProductDto> getCatalogListSearch(String searchName, int pageId, int productsPerPage);

    ProductDto getProductById(int productId);

    List<PageDto> getIdList(int productsPerPage);

    List<PageDto> getIdListSearch(final String searchName, int productsPerPage);

}
