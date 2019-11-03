package com.marpen.shop.dao;

import com.marpen.shop.model.Product;

import java.util.List;

public interface ProductDao {


        void addProduct(Product product);

        void updateProduct(Product product);

        void deleteProduct(int product_id);

        Product getProductById(int product_id);

        List<Product> getProductsListByPage(int pageid, int productsPerPage);

        List<Product> getProductsListByType(String type, int pageid, int productsPerPage);

        List<Product> getProductsListByName(String name, int pageid, int productsPerPage);

        int getAmountOfProducts();

        int getAmountOfProductsByType(String type);

        int getAmountOfProductsByName(String name);
}
