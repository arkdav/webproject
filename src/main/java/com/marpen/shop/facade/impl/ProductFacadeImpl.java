package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.CatalogVersionService;
import com.marpen.shop.service.PriceService;
import com.marpen.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductFacadeImpl implements ProductFacade {

    private ProductService productService;
    private PriceService priceService;
    private CatalogVersionService catalogVersionService;

    public ProductFacadeImpl(ProductService productService, PriceService priceService, CatalogVersionService catalogVersionService) {
        this.productService = productService;
        this.priceService = priceService;
        this.catalogVersionService=catalogVersionService;
    }

    @Override
    public List<ProductDto> getCatalogList(String searchName, int pageId, int productsPerPage) {
        List<Product> products;
        if(searchName==null){
            products = this.productService.getProductsListByPage(pageId, productsPerPage);
        } else{
            products = this.productService.getProductsListByName(searchName, pageId, productsPerPage);
        }
        List<ProductDto> list = new ArrayList<>(products.size());
        for (Product product :
                products) {
            ProductDto pr = getProductDtoFromProduct(product);
            list.add(pr);
        }
        return list;
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = this.productService.getProductById(productId);
        return getProductDtoFromProduct(product);
    }

    @Override
    public List<PageDto> getIdList(String searchName, int productsPerPage) {
        int amountOfProducts;
        if(searchName==null){
            amountOfProducts = this.productService.getAmountOfProducts();
        } else {
            amountOfProducts = this.productService.getAmountOfProductsByName(searchName);
        }
        return getPageDtoList(amountOfProducts,productsPerPage);
    }

    @Override
    public List<BusinessProductDto> getProductsList(){
        List<Product> products=productService.getProductsList();
        List<BusinessProductDto> list = new ArrayList<>(products.size());
        for (Product product :
                products) {
            BusinessProductDto pr = getBusinessProductDtoFromProduct(product);
            list.add(pr);
        }
        return list;
    }

    private ProductDto getProductDtoFromProduct(Product product) {
        return new ProductDto(product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getImageLink(),
                Double.toString(priceService.getPriceByProductId(product.getProductId()).getPrice())
        );
    }

    private BusinessProductDto getBusinessProductDtoFromProduct(Product product) {
        BusinessProductDto businessProductDto=new BusinessProductDto();
        businessProductDto.setProductId(product.getProductId());
        businessProductDto.setName(product.getName());
        businessProductDto.setDescription( product.getDescription());
        businessProductDto.setImageLink(product.getImageLink());
        businessProductDto.setPrice(Double.toString(priceService.getPriceByProductId(product.getProductId()).getPrice()));
        businessProductDto.setCatalogVersion(catalogVersionService.getCatalogVersionNameById(product.getCatverId()));
        return businessProductDto;
    }

    private List<PageDto> getPageDtoList(int amountOfProducts, int productsPerPage){
        int pagesNumber = amountOfProducts % productsPerPage == 0 ? amountOfProducts / productsPerPage : amountOfProducts / productsPerPage + 1;
        List<PageDto> list = new ArrayList<>(pagesNumber);
        for (int i = 0; i < pagesNumber; i++) {
            PageDto pr = new PageDto(i + 1);
            list.add(pr);
        }
        return list;
    }
}
