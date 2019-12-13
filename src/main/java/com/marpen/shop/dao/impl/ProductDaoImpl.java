package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import org.hibernate.Session;

import java.util.List;

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    private ProductDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsListByUserLogin(String userLogin) {
        String sql = "select * from products where user_login like :user_login order by product_id desc";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("user_login", userLogin).list();
        return products;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsListByPageAndCatVerId(int pageId, int productsPerPage, int catVerId) {
        String sql = "select * from products where catver_id like :catver_id limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsListByNameAndCatVerId(String name, int pageId, int productsPerPage, int catVerId) {
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and catver_id like :catver_id limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public int getAmountOfProductsByCatVerId(int catVerId) {
        String sql = "select * from products where catver_id like :catver_id";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("catver_id", catVerId).list();
        return products.size();
    }

    @Override
    public int getAmountOfProductsByNameAndCatVerId(String name, int catVerId) {
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and catver_id like :catver_id";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).setParameter("catver_id", catVerId).list();
        return products.size();
    }
}

