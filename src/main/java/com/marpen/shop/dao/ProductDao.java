package com.marpen.shop.dao;

import com.marpen.shop.model.Product;

import java.util.List;

public interface ProductDao {


    void create(Product product);

    void update(Product product);

    void delete(Product product);

    Product getProductById(int productId);

    List<Product> getProductsList();

    List<Product> getProductsListByPage(int pageId, int productsPerPage);

    List<Product> getProductsListByName(String name, int pageId, int productsPerPage);

    int getAmountOfProducts();

    int getAmountOfProductsByName(String name);
}
