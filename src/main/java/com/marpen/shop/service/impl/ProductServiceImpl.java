package com.marpen.shop.service.impl;

import com.marpen.shop.model.Product;
import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    public void deleteProduct(int productId) {
        this.productDao.deleteProduct(productId);
    }

    @Override
    public Product getProductById(int productId) {

        return this.productDao.getProductById(productId);
    }

    @Override
    public List<Product> getProductsListByPage(int pageId, int productsPerPage) {

        return this.productDao.getProductsListByPage(pageId,productsPerPage);
    }

    @Override
    public List<Product> getProductsListByType(String type, int pageId, int productsPerPage){

        return productDao.getProductsListByType(type, pageId, productsPerPage);
    }

    @Override
    public List<Product> getProductsListByName(String name, int pageId, int productsPerPage){

        return productDao.getProductsListByName(name, pageId, productsPerPage);
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
