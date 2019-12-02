package com.marpen.shop.service.impl;

import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProduct(Product product) {
        this.productDao.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        this.productDao.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productDao.get(productId);
        if (product != null) {
            this.productDao.delete(product);
        }
    }

    @Override
    public Product getProductById(int productId) {
        return this.productDao.get(productId);
    }

    @Override
    public List<Product> getProductsListByUserLogin(String userLogin) {
        return this.productDao.getProductsListByUserLogin(userLogin);
    }

    @Override
    public List<Product> getOnlineProductsListByPage(int pageId, int productsPerPage) {
        return this.productDao.getOnlineProductsListByPage(pageId, productsPerPage);
    }

    @Override
    public List<Product> getOnlineProductsListByName(String name, int pageId, int productsPerPage) {
        return productDao.getOnlineProductsListByName(name, pageId, productsPerPage);
    }

    @Override
    public int getOnlineAmountOfProducts() {
        return productDao.getOnlineAmountOfProducts();
    }

    @Override
    public int getOnlineAmountOfProductsByName(String name) {
        return productDao.getOnlineAmountOfProductsByName(name);
    }

}
