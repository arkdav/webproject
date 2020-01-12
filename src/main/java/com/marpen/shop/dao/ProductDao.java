package com.marpen.shop.dao;

import com.marpen.shop.model.Product;
import org.springframework.security.access.method.P;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {

    List<Product> getProductsListByUserLogin(int pageId, int productsPerPage, String userLogin);

    List<Product> getProductsListByUserLoginAndSearchName(String searchName, int pageId, int productsPerPage, String userLogin);

    List<Product> getProductsListByUserLoginAndCatVerId(int catVerId, int pageId, int productsPerPage, String userLogin);

    List<Product> getProductsListByUserLoginAndSearchNameAndCatVerId(int catVerId, String searchName, int pageId, int productsPerPage, String userLogin);

    List<Product> getProductsListBySearchNameAndCatVerIdWithSortName(String sortType, String searchName, int pageId, int productsPerPage, int catVerId);

    List<Product> getProductsListBySearchNameAndCatVerIdWithSortPrice(String sortType, String searchName, int pageId, int productsPerPage, int catVerId);

    List<Product> getProductsListByCatVerIdWithSortName(String sortType, int pageId, int productsPerPage, int catVerId);

    List<Product> getProductsListByCatVerIdWithSortPrice(String sortType, int pageId, int productsPerPage, int catVerId);

    List<Product> getAllProductsByCatVerId(int catVerId);

    List<Product> getAllProductsByNameAndCatVerId(String name, int catVerId);

    List<Product> getAllBusinessProducts(String userLogin);

    List<Product> getAllBusinessProductsByCatVerId(int catVerId, String userLogin);

    List<Product> getAllBusinessProductsByName(String name, String userLogin);

    List<Product> getAllBusinessProductsByNameAndCatVerId(String name, int catVerId, String userLogin);

}
