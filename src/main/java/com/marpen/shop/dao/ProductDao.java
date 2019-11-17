package com.marpen.shop.dao;

import com.marpen.shop.model.Product;

import java.util.List;

public interface ProductDao {


        void addProduct(Product product);

        void updateProduct(Product product);

        void deleteProduct(int productId);

        Product getProductById(int productId);

        List<Product> getProductsListByPage(int pageId, int productsPerPage);

        List<Product> getProductsListByName(String name, int pageId, int productsPerPage);

        int getAmountOfProducts();

        int getAmountOfProductsByName(String name);
}
