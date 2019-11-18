package com.marpen.shop.service;

import com.marpen.shop.model.Product;

import java.util.List;


public interface ProductService {

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    Product getProductById(int productId);

    List<Product> getProductsList();

    List<Product> getProductsListByPage(int pageid, int productsPerPage);

    List<Product> getProductsListByName(String name, int pageid, int productsPerPage);

    int getAmountOfProducts();

    int getAmountOfProductsByName(String name);
}
