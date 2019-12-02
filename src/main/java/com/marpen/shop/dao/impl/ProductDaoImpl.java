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
    public List<Product> getOnlineProductsListByPage(int pageId, int productsPerPage) {
        String sql = "select * from products where catver_id=1 limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).list();
        return products;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getOnlineProductsListByName(String name, int pageId, int productsPerPage) {
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and catver_id=1 limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).list();
        return products;
    }

    @Override
    public int getOnlineAmountOfProducts() {
        String sql = "select product_id from products where catver_id=1";
        return currentSession().createSQLQuery(sql).list().size();
    }

    @Override
    public int getOnlineAmountOfProductsByName(String name) {
        String nameForSql = "%" + name + "%";
        String sql = "select product_id from products where name like :name and catver_id=1";
        return currentSession().createSQLQuery(sql).setParameter("name", nameForSql).list().size();
    }
}

