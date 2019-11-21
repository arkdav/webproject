package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Transactional
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> entityClass;

    private SessionFactory sessionFactory;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
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

    public void delete(T domain) {
        getSession().delete(domain);
    }

    public void save(T domain) {
        getSession().save(domain);
    }

    public void update(T domain) {
        getSession().update(domain);
    }

    public T get(int id) {
        T object = (T) getSession().load(entityClass, id);
        return object;
    }

}
