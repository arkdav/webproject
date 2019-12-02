package com.marpen.shop.facade.impl;

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

import java.util.ArrayList;
import java.util.List;

public class ProductFacadeImpl implements ProductFacade {

    private ProductService productService;
    private PriceService priceService;
    private ToProductDto toProductDto;
    private ToBusinessProductDto toBusinessProductDto;
    private FromBusinessProductDto fromBusinessProductDto;
    private FromBusinessProductDtoToPrice fromBusinessProductDtoToPrice;
    private FromBusinessProductCreationDto fromBusinessProductCreationDto;
    private FromBusinessProductCreationDtoToPrice fromBusinessProductCreationDtoToPrice;

    public ProductFacadeImpl(ProductService productService, PriceService priceService,
                             ToProductDto toProductDto,
                             ToBusinessProductDto toBusinessProductDto, FromBusinessProductDto fromBusinessProductDto,
                             FromBusinessProductDtoToPrice fromBusinessProductDtoToPrice,
                             FromBusinessProductCreationDto fromBusinessProductCreationDto,
                             FromBusinessProductCreationDtoToPrice fromBusinessProductCreationDtoToPrice) {
        this.productService = productService;
        this.priceService = priceService;
        this.toProductDto = toProductDto;
        this.toBusinessProductDto = toBusinessProductDto;
        this.fromBusinessProductDto = fromBusinessProductDto;
        this.fromBusinessProductDtoToPrice = fromBusinessProductDtoToPrice;
        this.fromBusinessProductCreationDto = fromBusinessProductCreationDto;
        this.fromBusinessProductCreationDtoToPrice = fromBusinessProductCreationDtoToPrice;
    }

    @Override
    public List<ProductDto> getCatalogList(String searchName, int pageId, int productsPerPage) {
        List<Product> products;
        if (searchName == null) {
            products = this.productService.getOnlineProductsListByPage(pageId, productsPerPage);
        } else {
            products = this.productService.getOnlineProductsListByName(searchName, pageId, productsPerPage);
        }
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
        int amountOfProducts;
        if (searchName == null) {
            amountOfProducts = this.productService.getOnlineAmountOfProducts();
        } else {
            amountOfProducts = this.productService.getOnlineAmountOfProductsByName(searchName);
        }
        return toPageDtoList(amountOfProducts, productsPerPage);
    }

    @Override
    public List<BusinessProductDto> getProductsListByUserLogin(String userLogin) {
        List<Product> products = productService.getProductsListByUserLogin(userLogin);
        List<BusinessProductDto> list = new ArrayList<>(products.size());
        for (Product product :
                products) {
            BusinessProductDto pr = toBusinessProductDto.convert(product);
            list.add(pr);
        }
        return list;
    }

    @Override
    public BusinessProductDto getBusinessProductDtoById(int productId) {
        Product product = productService.getProductById(productId);
        return toBusinessProductDto.convert(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productService.deleteProduct(productId);
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
    public void createProduct(String userLogin, BusinessProductCreationDto businessProductCreationDto) {
        businessProductCreationDto.setUserLogin(userLogin);
        Product product = fromBusinessProductCreationDto.convert(businessProductCreationDto);
        productService.createProduct(product);
        Price price = fromBusinessProductCreationDtoToPrice.convert(businessProductCreationDto);
        price.setProductId(product.getProductId());
        price.setProductId(product.getProductId());
        priceService.savePrice(price);
    }

    private List<PageDto> toPageDtoList(int amountOfProducts, int productsPerPage) {
        int pagesNumber = amountOfProducts % productsPerPage == 0 ? amountOfProducts / productsPerPage : amountOfProducts / productsPerPage + 1;
        List<PageDto> list = new ArrayList<>(pagesNumber);
        for (int i = 0; i < pagesNumber; i++) {
            PageDto pr = new PageDto(i + 1);
            list.add(pr);
        }
        return list;
    }
}
