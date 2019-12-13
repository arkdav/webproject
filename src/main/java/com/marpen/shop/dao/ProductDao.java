package com.marpen.shop.dao;

import com.marpen.shop.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {

    List<Product> getProductsListByUserLogin(String userLogin);

    List<Product> getProductsListByPageAndCatVerId(int pageId, int productsPerPage, int catVerId);

    List<Product> getProductsListByNameAndCatVerId(String name, int pageId, int productsPerPage, int catVerId);

    int getAmountOfProductsByCatVerId(int catVerId);

    int getAmountOfProductsByNameAndCatVerId(String name, int catVerId);
}
