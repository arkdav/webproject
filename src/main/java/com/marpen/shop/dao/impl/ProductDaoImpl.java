package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    private Session session;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        if (super.getSessionFactory() == null) {
            super.setSessionFactory(sessionFactory);

        }
        this.session = super.getSession();
    }

    private Session currentSession() {
        return this.session;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsListByUserId(int userId) {
        String sql = "select * from products where user_id like :user_id order by product_id desc";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("user_id", userId).list();
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
        return session.createSQLQuery(sql).list().size();
    }

    @Override
    public int getOnlineAmountOfProductsByName(String name) {
        String nameForSql = "%" + name + "%";
        String sql = "select product_id from products where name like :name and catver_id=1";
        return session.createSQLQuery(sql).setParameter("name", nameForSql).list().size();
    }
}

