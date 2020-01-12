package com.marpen.shop.service.impl;

import com.marpen.shop.dao.CatalogVersionDao;
import com.marpen.shop.dao.PriceDao;
import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.ProductService;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
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
            File file = new File(System.getenv("CATALINA_HOME") +
                    "\\webapps\\webproject\\resources\\images\\" +
                    productDao.get(productId).getImageLink());
            file.delete();
            this.productDao.delete(product);
        }
    }

    @Override
    public void changeVersionProduct(int productId) {
        Product product = productDao.get(productId);
        if (product != null) {
            int catVerId = product.getCatverId();
            catVerId = catVerId == catalogVersionDao.getCatalogVersionByName("online").getCatverId() ?
                    catalogVersionDao.getCatalogVersionByName("offline").getCatverId()
                    : catalogVersionDao.getCatalogVersionByName("online").getCatverId();
            product.setCatverId(catVerId);
            productDao.update(product);
        }
    }

    @Override
    public Product getProductById(int productId) {
        return this.productDao.get(productId);
    }

    @Override
    public List<Product> getProductsList(String catVer, String searchName, int pageId, int productsPerPage, String userLogin) {
        List<Product> products;
        if(catVer.isEmpty()){
            if (searchName.isEmpty()) {
                products = this.productDao.getProductsListByUserLogin(pageId, productsPerPage, userLogin);
            } else {
                products = this.productDao.getProductsListByUserLoginAndSearchName(searchName, pageId, productsPerPage, userLogin);
            }
        } else {
            int catVerId = catalogVersionDao.getCatalogVersionByName(catVer).getCatverId();
            if (searchName.isEmpty()) {
                products = this.productDao.getProductsListByUserLoginAndCatVerId(catVerId, pageId, productsPerPage, userLogin);
            } else {
                products = this.productDao.getProductsListByUserLoginAndSearchNameAndCatVerId(catVerId, searchName, pageId, productsPerPage, userLogin);
            }
        }
        return products;
    }

    @Override
    public List<Product> getOnlineProductsList(String sortBy, String sortType, String searchName, int pageId, int productsPerPage) {
        int catVerId = catalogVersionDao.getCatalogVersionByName("online").getCatverId();
        List<Product> products = new ArrayList<>();
        if(searchName.isEmpty()) {
            if (sortBy.equals("name")) {
                products=productDao.getProductsListByCatVerIdWithSortName(sortType,pageId,productsPerPage,catVerId);
            } else if (sortBy.equals("price")) {
                products=productDao.getProductsListByCatVerIdWithSortPrice(sortType,pageId,productsPerPage,catVerId);
            }
        } else {
            if (sortBy.equals("name")) {
                products=productDao.getProductsListBySearchNameAndCatVerIdWithSortName(sortType, searchName, pageId,productsPerPage,catVerId);
            } else if (sortBy.equals("price")) {
                products=productDao.getProductsListBySearchNameAndCatVerIdWithSortPrice(sortType, searchName, pageId,productsPerPage,catVerId);
            }
        }
        return products;
    }

    @Override
    public int getOnlineAmountOfProducts(String searchName) {
        List<Product> products;
        int catVerId = catalogVersionDao.getCatalogVersionByName("online").getCatverId();
        if (searchName.isEmpty()) {
            products = productDao.getAllProductsByCatVerId(catVerId);
        } else {
            products = productDao.getAllProductsByNameAndCatVerId(searchName, catVerId);
        }
        return products != null ? products.size() : 0;
    }

    @Override
    public int getBusinessAmountOfProductsByUserLoginAndSearchNameAndCatVerId(String catVer, String searchName, String userLogin) {
        List<Product> products;
        if (!searchName.isEmpty()) {
            if (catVer.isEmpty()) {
                products = productDao.getAllBusinessProductsByName(searchName, userLogin);
            } else {
                int catVerId = catalogVersionDao.getCatalogVersionByName(catVer).getCatverId();
                products = productDao.getAllBusinessProductsByNameAndCatVerId(searchName, catVerId, userLogin);
            }
        } else {
            if (catVer.isEmpty()) {
                products = productDao.getAllBusinessProducts(userLogin);
            } else {
                int catVerId = catalogVersionDao.getCatalogVersionByName(catVer).getCatverId();
                products = productDao.getAllBusinessProductsByCatVerId(catVerId, userLogin);
            }
        }
        return products != null ? products.size() : 0;
    }
}
