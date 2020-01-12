package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> entityClass;
    private SessionFactory sessionFactory;

    public GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() {
        Session session;
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException | NullPointerException e) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    @Override
    public void delete(T domain) {
        getSession().delete(domain);
    }

    @Override
    public void save(T domain) {
        getSession().save(domain);
    }

    @Override
    public void update(T domain) {
        getSession().update(domain);
    }

    @Override
    public T get(Object id) {
        T object = (T) getSession().load(entityClass, (Serializable) id);
        return object;
    }

    @Override
    public List<T> getAll(){
        return getSession().createQuery("FROM "+ entityClass.getSimpleName()).list();
    }

}
