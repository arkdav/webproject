package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.ProductDao;
import com.marpen.shop.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addProduct(Product product) {
       currentSession().persist(product);
    }

    @Override
    public void updateProduct(Product product){
        currentSession().update(product);
    }

    @Override
    public void deleteProduct(int productId){
        Product product=(Product) currentSession().load(Product.class,productId);
        if(product!=null){
            currentSession().delete(product);
        }
    }

    @Override
    public Product getProductById(int productId) {
       Product p= (Product) currentSession().load(Product.class, productId);
       return p;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsListByPage(int pageId, int productsPerPage){
        String sql="select * from products where catver_id=1 limit "+(pageId-1)+","+productsPerPage;
        List<Product> products=currentSession().createSQLQuery(sql).addEntity(Product.class).list();
       return products;

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsListByName(String name, int pageId, int productsPerPage) {
        String nameForSql="%"+name+"%";
        String sql="select * from products where name like :name and catver_id=1 limit "+(pageId-1)+","+productsPerPage;
        List <Product>products=currentSession().createSQLQuery(sql).addEntity(Product.class).setParameter("name", nameForSql).list();
        return products;
    }

    @Override
    public int getAmountOfProducts(){
        String sql="select product_id from products";
        return currentSession().createSQLQuery(sql).list().size();
    }

    @Override
    public int getAmountOfProductsByName(String name){
        String nameForSql="%"+name+"%";
        String sql="select product_id from products where name like :name";
        return currentSession().createSQLQuery(sql).setParameter("name", nameForSql).list().size();
    }

}

