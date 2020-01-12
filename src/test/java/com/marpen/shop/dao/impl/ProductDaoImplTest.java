package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/test-dao-persistance-context.xml"})
@Transactional
public class ProductDaoImplTest {

    @Autowired
    private ProductDao productDao;

    private static final int PRODUCT_ID_FIRST = 1;
    private static final int PRODUCT_ID_SECOND = 2;
    private static final int PRODUCT_ID_THIRD = 3;
    private static final int PRODUCT_ID_FOURTH = 4;
    private static final String NAME_FIRST = "Pencil";
    private static final String NAME_SECOND = "Pen";
    private static final String NAME_THIRD = "Sketchbook";
    private static final String NAME_FOURTH = "Paints";
    private static final String DESCRIPTION_FIRST = "information about pencil";
    private static final String DESCRIPTION_SECOND = "information about pen";
    private static final String DESCRIPTION_THIRD = "information about sketchbook";
    private static final String DESCRIPTION_FOURTH = "information about paints";
    private static final String IMAGE_URL_FIRST = "Pencil.jpg";
    private static final String IMAGE_URL_SECOND = "Pen.jpg";
    private static final String IMAGE_URL_THIRD = "Sketchbook.jpg";
    private static final String IMAGE_URL_FOURTH = "Paints.jpg";
    private static final int CAT_VER_ID_FIRST = 1;
    private static final int CAT_VER_ID_SECOND = 1;
    private static final int CAT_VER_ID_THIRD = 2;
    private static final int CAT_VER_ID_FOURTH = 1;
    private static final String USER_LOGIN = "misha";
    private static final String USER_LOGIN_NO = "abrakadabra";
    private static final String SEARCH_NAME = "pen";
    private static final String SEARCH_NAME_NO = "abrakadabra";
    private static final Product PRODUCT_FIRST =
            new Product(PRODUCT_ID_FIRST, NAME_FIRST, DESCRIPTION_FIRST, IMAGE_URL_FIRST, CAT_VER_ID_FIRST, USER_LOGIN);
    private static final Product PRODUCT_SECOND =
            new Product(PRODUCT_ID_SECOND, NAME_SECOND, DESCRIPTION_SECOND, IMAGE_URL_SECOND, CAT_VER_ID_SECOND, USER_LOGIN);
    private static final Product PRODUCT_THIRD =
            new Product(PRODUCT_ID_THIRD, NAME_THIRD, DESCRIPTION_THIRD, IMAGE_URL_THIRD, CAT_VER_ID_THIRD, USER_LOGIN);
    private static final Product PRODUCT_FOURTH =
            new Product(PRODUCT_ID_FOURTH, NAME_FOURTH, DESCRIPTION_FOURTH, IMAGE_URL_FOURTH, CAT_VER_ID_FOURTH, USER_LOGIN);

    @Test
    public void getAllProductsByCatVerId() {
        List<Product> productsByCatVerId = new ArrayList<>();
        productsByCatVerId.add(PRODUCT_FIRST);
        productsByCatVerId.add(PRODUCT_SECOND);
        productsByCatVerId.add(PRODUCT_FOURTH);
        List<Product> actual = productDao.getAllProductsByCatVerId(CAT_VER_ID_FIRST);
        Assert.assertEquals(productsByCatVerId, actual);
    }

    @Test
    public void getAllProductsByNameAndCatVerId_Good() {
        List<Product> productsByNameAndCatVerId = new ArrayList<>();
        productsByNameAndCatVerId.add(PRODUCT_FIRST);
        productsByNameAndCatVerId.add(PRODUCT_SECOND);
        List<Product> actual = productDao.getAllProductsByNameAndCatVerId(SEARCH_NAME, CAT_VER_ID_FIRST);
        Assert.assertEquals(productsByNameAndCatVerId, actual);
    }

    @Test
    public void getAllProductsByNameAndCatVerId_Empty() {
        List<Product> productsByNameAndCatVerId = new ArrayList<>();
        List<Product> actual = productDao.getAllProductsByNameAndCatVerId(SEARCH_NAME_NO, CAT_VER_ID_FIRST);
        Assert.assertEquals(productsByNameAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProducts_Good() {
        List<Product> productsByLogin = new ArrayList<>();
        productsByLogin.add(PRODUCT_FIRST);
        productsByLogin.add(PRODUCT_SECOND);
        productsByLogin.add(PRODUCT_THIRD);
        productsByLogin.add(PRODUCT_FOURTH);
        List<Product> actual = productDao.getAllBusinessProducts(USER_LOGIN);
        Assert.assertEquals(productsByLogin, actual);
    }

    @Test
    public void getAllBusinessProducts_Empty() {
        List<Product> productsByLogin = new ArrayList<>();
        List<Product> actual = productDao.getAllBusinessProducts(USER_LOGIN_NO);
        Assert.assertEquals(productsByLogin, actual);
    }

    @Test
    public void getAllBusinessProductsByCatVerId_GoodThree() {
        List<Product> productsByLoginAndCatVerId = new ArrayList<>();
        productsByLoginAndCatVerId.add(PRODUCT_FIRST);
        productsByLoginAndCatVerId.add(PRODUCT_SECOND);
        productsByLoginAndCatVerId.add(PRODUCT_FOURTH);
        List<Product> actual = productDao.getAllBusinessProductsByCatVerId(CAT_VER_ID_FIRST, USER_LOGIN);
        Assert.assertEquals(productsByLoginAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProductsByCatVerId_GoodOne() {
        List<Product> productsByLoginAndCatVerId = new ArrayList<>();
        productsByLoginAndCatVerId.add(PRODUCT_THIRD);
        List<Product> actual = productDao.getAllBusinessProductsByCatVerId(CAT_VER_ID_THIRD, USER_LOGIN);
        Assert.assertEquals(productsByLoginAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProductsByCatVerId_Empty() {
        List<Product> productsByLoginAndCatVerId = new ArrayList<>();
        List<Product> actual = productDao.getAllBusinessProductsByCatVerId(CAT_VER_ID_THIRD, USER_LOGIN_NO);
        Assert.assertEquals(productsByLoginAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProductsByName_Good() {
        List<Product> productsByNameAndLogin = new ArrayList<>();
        productsByNameAndLogin.add(PRODUCT_FIRST);
        productsByNameAndLogin.add(PRODUCT_SECOND);
        List<Product> actual = productDao.getAllBusinessProductsByName(SEARCH_NAME, USER_LOGIN);
        Assert.assertEquals(productsByNameAndLogin, actual);
    }

    @Test
    public void getAllBusinessProductsByName_Empty() {
        List<Product> productsByNameAndLogin = new ArrayList<>();
        List<Product> actual = productDao.getAllBusinessProductsByName(SEARCH_NAME_NO, USER_LOGIN);
        Assert.assertEquals(productsByNameAndLogin, actual);
    }

    @Test
    public void getAllBusinessProductsByNameAndCatVerId_Good() {
        List<Product> productsByNameAndLoginAndCatVerId = new ArrayList<>();
        productsByNameAndLoginAndCatVerId.add(PRODUCT_FIRST);
        productsByNameAndLoginAndCatVerId.add(PRODUCT_SECOND);
        List<Product> actual = productDao.getAllBusinessProductsByNameAndCatVerId(SEARCH_NAME, CAT_VER_ID_FIRST, USER_LOGIN);
        Assert.assertEquals(productsByNameAndLoginAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProductsByNameAndCatVerId_EmptyBecauseOfName() {
        List<Product> productsByNameAndLoginAndCatVerId = new ArrayList<>();
        List<Product> actual = productDao.getAllBusinessProductsByNameAndCatVerId(SEARCH_NAME_NO, CAT_VER_ID_FIRST, USER_LOGIN);
        Assert.assertEquals(productsByNameAndLoginAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProductsByNameAndCatVerId_EmptyBecauseOfCatVerId() {
        List<Product> productsByNameAndLoginAndCatVerId = new ArrayList<>();
        List<Product> actual = productDao.getAllBusinessProductsByNameAndCatVerId(SEARCH_NAME, CAT_VER_ID_THIRD, USER_LOGIN);
        Assert.assertEquals(productsByNameAndLoginAndCatVerId, actual);
    }

    @Test
    public void getAllBusinessProductsByNameAndCatVerId_EmptyBecauseOfLogin() {
        List<Product> productsByNameAndLoginAndCatVerId = new ArrayList<>();
        List<Product> actual = productDao.getAllBusinessProductsByNameAndCatVerId(SEARCH_NAME, CAT_VER_ID_FIRST, USER_LOGIN_NO);
        Assert.assertEquals(productsByNameAndLoginAndCatVerId, actual);
    }

}