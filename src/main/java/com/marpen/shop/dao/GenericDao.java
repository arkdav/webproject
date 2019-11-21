package com.marpen.shop.dao;

public interface GenericDao<T> {

    void save(T domain);

    void update(T domain);

    void delete(T domain);

    T get(int id);
}