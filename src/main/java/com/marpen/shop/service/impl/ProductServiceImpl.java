package com.marpen.shop.service.impl;

import com.marpen.shop.dao.CatalogVersionDao;
import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private CatalogVersionDao catalogVersionDao;

    public ProductServiceImpl(ProductDao productDao, CatalogVersionDao catalogVersionDao) {
        this.productDao = productDao;
        this.catalogVersionDao = catalogVersionDao;
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
        int catVerId = catalogVersionDao.getCatalogVersionByName("online").getCatverId();
        return this.productDao.getProductsListByPageAndCatVerId(pageId, productsPerPage, catVerId);
    }

    @Override
    public List<Product> getOnlineProductsListByName(String name, int pageId, int productsPerPage) {
        int catVerId = catalogVersionDao.getCatalogVersionByName("online").getCatverId();
        return productDao.getProductsListByNameAndCatVerId(name, pageId, productsPerPage, catVerId);
    }

    @Override
    public int getOnlineAmountOfProducts() {
        int catVerId = catalogVersionDao.getCatalogVersionByName("online").getCatverId();
        return productDao.getAmountOfProductsByCatVerId(catVerId);
    }

    @Override
    public int getOnlineAmountOfProductsByName(String name) {
        int catVerId = catalogVersionDao.getCatalogVersionByName("online").getCatverId();
        return productDao.getAmountOfProductsByNameAndCatVerId(name, catVerId);
    }

}
