package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.ToPageDto;
import com.marpen.shop.converter.ToProductDto;
import com.marpen.shop.converter.forms.FromBusinessProductCreationDto;
import com.marpen.shop.converter.forms.FromBusinessProductCreationDtoToPrice;
import com.marpen.shop.converter.forms.FromBusinessProductDto;
import com.marpen.shop.converter.forms.FromBusinessProductDtoToPrice;
import com.marpen.shop.converter.forms.ToBusinessProductDto;
import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Price;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.PriceService;
import com.marpen.shop.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductFacadeImpl implements ProductFacade {

    private ProductService productService;
    private PriceService priceService;
    private ToProductDto toProductDto;
    private ToBusinessProductDto toBusinessProductDto;
    private ToPageDto toPageDto;
    private FromBusinessProductDto fromBusinessProductDto;
    private FromBusinessProductDtoToPrice fromBusinessProductDtoToPrice;
    private FromBusinessProductCreationDto fromBusinessProductCreationDto;
    private FromBusinessProductCreationDtoToPrice fromBusinessProductCreationDtoToPrice;

    public ProductFacadeImpl(ProductService productService, PriceService priceService,
                             ToProductDto toProductDto, ToBusinessProductDto toBusinessProductDto,
                             ToPageDto toPageDto, FromBusinessProductDto fromBusinessProductDto,
                             FromBusinessProductDtoToPrice fromBusinessProductDtoToPrice,
                             FromBusinessProductCreationDto fromBusinessProductCreationDto,
                             FromBusinessProductCreationDtoToPrice fromBusinessProductCreationDtoToPrice) {
        this.productService = productService;
        this.priceService = priceService;
        this.toProductDto = toProductDto;
        this.toBusinessProductDto = toBusinessProductDto;
        this.toPageDto=toPageDto;
        this.fromBusinessProductDto = fromBusinessProductDto;
        this.fromBusinessProductDtoToPrice = fromBusinessProductDtoToPrice;
        this.fromBusinessProductCreationDto = fromBusinessProductCreationDto;
        this.fromBusinessProductCreationDtoToPrice = fromBusinessProductCreationDtoToPrice;
    }

    @Override
    public List<ProductDto> getCatalogList(String searchName, int pageId, int productsPerPage, String sort) {
        List<Product> products;
        String sortBy="name", sortType="asc";
        switch (sort) {
            case "alpza":
                    sortBy = "name";
                    sortType="desc";
                    break;
            case "priceaz":
                    sortBy = "price";
                    sortType="asc";
                    break;
            case "priceza":
                    sortBy = "price";
                    sortType="desc";
                    break;
        }
        products = this.productService.getOnlineProductsList(sortBy, sortType, searchName, pageId, productsPerPage);
        List<ProductDto> list = new ArrayList<>(products.size());
        for (Product product :
                products) {
            ProductDto pr = toProductDto.convert(product);
            list.add(pr);
        }
        return list;
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = this.productService.getProductById(productId);
        return toProductDto.convert(product);
    }

    @Override
    public List<PageDto> getCatalogPagesList(String searchName, int productsPerPage) {
        int amountOfProducts= this.productService.getOnlineAmountOfProducts(searchName);
        return toPageDto.convert(amountOfProducts, productsPerPage);
    }

    @Override
    public List<BusinessProductDto> getBusinessProductsList(String catVer, String searchName, int pageId, int productsPerPage, String userLogin) {
        List<Product> products;
       products=productService.getProductsList(catVer,searchName,pageId,productsPerPage,userLogin);
        List<BusinessProductDto> list = new ArrayList<>(products.size());
        for (Product product :
                products) {
            BusinessProductDto pr = toBusinessProductDto.convert(product);
            list.add(pr);
        }
        return list;
    }

    @Override
    public List<PageDto> getBusinessPagesList(String catVer, String searchName, int productsPerPage, String userLogin) {
        int amountOfProducts= this.productService.getBusinessAmountOfProductsByUserLoginAndSearchNameAndCatVerId(catVer, searchName, userLogin);
        return toPageDto.convert(amountOfProducts, productsPerPage);
    }

    @Override
    public BusinessProductDto getBusinessProductDtoById(int productId) {
        Product product = productService.getProductById(productId);
        return toBusinessProductDto.convert(product);
    }

    @Override
    public boolean deleteProducts(String [] productIds) {
        boolean isDeleted=true;
        for (String id : productIds) {
            if(productService.getProductById(Integer.parseInt(id))!=null){
                productService.deleteProduct(Integer.parseInt(id));
            } else {
                isDeleted=false;
            }
        }
        return isDeleted;
    }

    @Override
    public void updateProduct(String userLogin, BusinessProductDto businessProductDto) {
        businessProductDto.setUserLogin(userLogin);
        Product product = fromBusinessProductDto.convert(businessProductDto);
        Price price = fromBusinessProductDtoToPrice.convert(businessProductDto);
        productService.updateProduct(product);
        priceService.updatePrice(price);
    }

    @Override
    public boolean changeProductsVersion(String [] productIds) {
        boolean isChanged=true;
        for (String id : productIds) {
            if(productService.getProductById(Integer.parseInt(id))!=null){
                productService.changeVersionProduct(Integer.parseInt(id));
            } else {
                isChanged=false;
            }
        }
        return isChanged;
    }

    @Override
    public void createProduct(String userLogin, BusinessProductCreationDto businessProductCreationDto) {
        businessProductCreationDto.setUserLogin(userLogin);
        Product product = fromBusinessProductCreationDto.convert(businessProductCreationDto);
        productService.createProduct(product);
        Price price = fromBusinessProductCreationDtoToPrice.convert(businessProductCreationDto);
        price.setProductId(product.getProductId());
        price.setProductId(product.getProductId());
        priceService.savePrice(price);
    }
}
