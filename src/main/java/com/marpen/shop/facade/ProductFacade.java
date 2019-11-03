package com.marpen.shop.facade;

import com.marpen.shop.dto.ImageDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    List<ProductDto> getCatalogList(int pageid, int productsPerPage);

    List<ProductDto> getCatalogListSearch(String searchName, int pageid, int productsPerPage);

    ProductDto getProductById(int product_id);

    List<PageDto> getIdList(int productsPerPage);

    List<PageDto> getIdListSearch(final String searchName, int productsPerPage);

    List<ImageDto> getImageListByProductId(int product_id);
}
