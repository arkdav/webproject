package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    private ProductDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<Product> getProductsListByUserLogin(int pageId, int productsPerPage, String userLogin) {
        String sql = "select * from products where user_login like :user_login order by product_id desc"
                + " limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("user_login", userLogin).list();
        return products;
    }

    @Override
    public List<Product> getProductsListByUserLoginAndSearchName(String searchName, int pageId, int productsPerPage, String userLogin) {
        String nameForSql = "%" + searchName + "%";
        String sql = "select * from products where user_login like :user_login and name like :name order by product_id desc"
                + " limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql)
                .addEntity(Product.class).setParameter("user_login", userLogin).setParameter("name", nameForSql).list();
        return products;
    }

    @Override
    public List<Product> getProductsListByUserLoginAndCatVerId(int catVerId, int pageId, int productsPerPage, String userLogin) {
        String sql = "select * from products where user_login like :user_login and catver_id like :catver_id order by product_id desc"
                + " limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql)
                .addEntity(Product.class).setParameter("user_login", userLogin).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public List<Product> getProductsListByUserLoginAndSearchNameAndCatVerId(int catVerId, String searchName, int pageId, int productsPerPage, String userLogin) {
        String nameForSql = "%" + searchName + "%";
        String sql = "select * from products where user_login like :user_login and catver_id like :catver_id and name like :name order by product_id desc"
                + " limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql)
                .addEntity(Product.class).setParameter("user_login", userLogin).setParameter("catver_id", catVerId).setParameter("name", nameForSql).list();
        return products;
    }

    @Override
    public List<Product> getProductsListByCatVerIdWithSortName(String sort, int pageId, int productsPerPage, int catVerId) {
        String sql = "select * from products where catver_id like :catver_id order by name "+sort+" limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public List<Product> getProductsListByCatVerIdWithSortPrice(String sort, int pageId, int productsPerPage, int catVerId) {
        String sql = "select products.product_id, products.`name`, products.`description`, products.imageurl, products.catver_id, products.user_login" +
                " from products inner join price on products.product_id=price.product_id" +
                " where catver_id like :catver_id" +
                " order by price.price " +sort+" limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public List<Product> getProductsListBySearchNameAndCatVerIdWithSortName(String sort, String name, int pageId, int productsPerPage, int catVerId) {
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and catver_id like :catver_id order by name " + sort+" limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public List<Product> getProductsListBySearchNameAndCatVerIdWithSortPrice(String sort, String name, int pageId, int productsPerPage, int catVerId) {
        String nameForSql = "%" + name + "%";
        String sql = "select products.product_id, products.`name`, products.`description`, products.imageurl, products.catver_id, products.user_login" +
                " from products inner join price on products.product_id=price.product_id" +
                " where catver_id like :catver_id and name like :name" +
                " order by price " +sort+" limit " + (pageId - 1) + "," + productsPerPage;
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).setParameter("catver_id", catVerId).list();
       return products;
    }


    @Override
    public List<Product> getAllProductsByCatVerId(int catVerId) {
        String sql = "select * from products where catver_id like :catver_id";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public List<Product> getAllProductsByNameAndCatVerId(String name, int catVerId) {
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and catver_id like :catver_id";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).setParameter("catver_id", catVerId).list();
        return products;
    }

    @Override
    public List<Product> getAllBusinessProducts(String userLogin) {
        String sql = "select * from products where user_login like :user_login";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("user_login", userLogin).list();
        return products;
    }

    @Override
    public  List<Product> getAllBusinessProductsByCatVerId(int catVerId, String userLogin){
        String sql = "select * from products where catver_id like :catver_id and user_login like :user_login";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("catver_id", catVerId).setParameter("user_login", userLogin).list();
        return products;
    }

    @Override
    public List<Product> getAllBusinessProductsByName(String name, String userLogin) {
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and user_login like :user_login";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).setParameter("user_login", userLogin).list();
        return products;
    }

    @Override
    public List<Product> getAllBusinessProductsByNameAndCatVerId(String name, int catVerId, String userLogin){
        String nameForSql = "%" + name + "%";
        String sql = "select * from products where name like :name and user_login like :user_login and catver_id like :catver_id";
        List<Product> products = currentSession().createSQLQuery(sql).addEntity(Product.class)
                .setParameter("name", nameForSql)
                .setParameter("user_login", userLogin)
                .setParameter("catver_id", catVerId)
                .list();
        return products;
    }
}

