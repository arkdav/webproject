package com.marpen.shop.dao;

import com.marpen.shop.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {

    List<Product> getProductsList();

    List<Product> getOnlineProductsListByPage(int pageId, int productsPerPage);

    List<Product> getOnlineProductsListByName(String name, int pageId, int productsPerPage);

    List<Integer> getIdList();

    int getOnlineAmountOfProducts();

    int getOnlineAmountOfProductsByName(String name);
}
