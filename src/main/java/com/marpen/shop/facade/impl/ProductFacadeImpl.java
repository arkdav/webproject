package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.ImageDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Image;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.ImageService;
import com.marpen.shop.service.PriceService;
import com.marpen.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductFacadeImpl implements ProductFacade {

    private ProductService productService;
    private PriceService priceService;
    private ImageService imageService;

    public ProductFacadeImpl(ProductService productService, PriceService priceService, ImageService imageService) {
        this.productService = productService;
        this.priceService = priceService;
        this.imageService = imageService;
    }

    @Override
    public List<ProductDto> getCatalogList(int pageId, int productsPerPage) {
        List<Product> products = this.productService.getProductsListByPage(pageId, productsPerPage);
        List<ProductDto> list = new ArrayList<>(products.size());
        for (Product product :
                products) {
            ProductDto pr = getProductDtoFromProduct(product);
            list.add(pr);
        }
        return list;
    }

    @Override
    public List<ProductDto> getCatalogListSearch(String searchName, int pageId, int productsPerPage) {
        List<Product> products = this.productService.getProductsListByName(searchName, pageId, productsPerPage);
        System.out.println(products);
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
    public List<PageDto> getIdList(int productsPerPage) {
        int amountOfProducts = this.productService.getAmountOfProducts();
        return getPageDtoList(amountOfProducts,productsPerPage);
    }

    @Override
    public List<PageDto> getIdListSearch(final String searchName, int productsPerPage) {
        int amountOfProducts = this.productService.getAmountOfProductsByName(searchName);
        return getPageDtoList(amountOfProducts,productsPerPage);
    }

    @Override
    public List<ImageDto> getImageListByProductId(int productId) {
        List<Image> images = this.imageService.getImageListByProductId(productId);
        List<ImageDto> list = new ArrayList<>(images.size());
        for (Image image :
                images) {
            ImageDto im = new ImageDto(image.getImageId(),
                    image.getLink()
            );
            list.add(im);
        }
        return list;
    }

    private ProductDto getProductDtoFromProduct(Product product) {
        return new ProductDto(product.getProductId(),
                product.getName(),
                product.getInformation(),
                product.getType(),
                priceService.getPriceByProductId(product.getProductId()).getPrice(),
                imageService.getImageByProductId(product.getProductId()).getLink()
        );
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

    public ProductService getProductService() {
        return productService;
    }

    public PriceService getPriceService() {
        return priceService;
    }

    public ImageService getImageService() {
        return imageService;
    }
}
