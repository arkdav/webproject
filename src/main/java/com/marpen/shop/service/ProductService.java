package com.marpen.shop.service;

import com.marpen.shop.model.Product;

import java.util.List;


public interface ProductService {

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    Product getProductById(int productId);

    List<Product> getProductsListByUserLogin(String userLogin);

    List<Product> getOnlineProductsListByPage(int pageid, int productsPerPage);

    List<Product> getOnlineProductsListByName(String name, int pageid, int productsPerPage);

    int getOnlineAmountOfProducts();

    int getOnlineAmountOfProductsByName(String name);

}
