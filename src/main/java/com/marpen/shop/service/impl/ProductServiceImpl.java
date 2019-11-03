package com.marpen.shop.service.impl;

import com.marpen.shop.model.Product;
import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product product) {
        this.productDao.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        this.productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int product_id) {
        this.productDao.deleteProduct(product_id);
    }

    @Override
    public Product getProductById(int product_id) {

        return this.productDao.getProductById(product_id);
    }

    @Override
    public List<Product> getProductsListByPage(int pageid, int productsPerPage) {

        return this.productDao.getProductsListByPage(pageid,productsPerPage);
    }

    @Override
    public List<Product> getProductsListByType(String type, int pageid, int productsPerPage){

        return productDao.getProductsListByType(type, pageid, productsPerPage);
    }

    @Override
    public List<Product> getProductsListByName(String name, int pageid, int productsPerPage){

        return productDao.getProductsListByName(name, pageid, productsPerPage);
    }

    @Override
    public int getAmountOfProducts(){
        return  productDao.getAmountOfProducts();
    }

    @Override
    public int getAmountOfProductsByType(String type){
        return  productDao.getAmountOfProductsByType(type);
    }

    @Override
    public int getAmountOfProductsByName(String name){
        return productDao.getAmountOfProductsByName(name);
    }
}
