package com.marpen.shop.dao;

import java.util.List;

public interface GenericDao<T> {

    void save(T domain);

    void update(T domain);

    void delete(T domain);

    T get(Object id);

    List<T> getAll();
}