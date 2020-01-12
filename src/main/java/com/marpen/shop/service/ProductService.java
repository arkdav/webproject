package com.marpen.shop.service;

import com.marpen.shop.model.Product;

import java.util.List;


public interface ProductService {

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    void changeVersionProduct(int productId);

    Product getProductById(int productId);

    List<Product> getProductsList(String catVer, String searchName, int pageId, int productsPerPage, String userLogin);

    List<Product> getOnlineProductsList(String sortBy, String sortType, String searchName, int pageId, int productsPerPage);

    int getOnlineAmountOfProducts(String searchName);

    int getBusinessAmountOfProductsByUserLoginAndSearchNameAndCatVerId(String catVer, String searchName, String userLogin);
}
