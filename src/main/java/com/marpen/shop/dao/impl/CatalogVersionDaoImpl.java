package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CatalogVersionDao;
import com.marpen.shop.model.CatalogVersion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CatalogVersionDaoImpl implements CatalogVersionDao {

    private SessionFactory sessionFactory;

    public CatalogVersionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public CatalogVersion getCatalogVersionById(int catVerId) {
        String sql="Select * from catalogversion where catver_id like :catver_id";
        List<CatalogVersion> catalogVersions=currentSession().createSQLQuery(sql).addEntity(CatalogVersion.class).setParameter("catver_id", catVerId).list();
        return  catalogVersions.isEmpty()?null:catalogVersions.get(0);
    }

    @Override
    public int getCatalogVersionIdByName(String name) {
        String sql="Select * from catalogversion where name like :name";
        List<CatalogVersion> catalogVersions=currentSession().createSQLQuery(sql).addEntity(CatalogVersion.class).setParameter("name", name).list();
        return  catalogVersions.isEmpty()?null:catalogVersions.get(0).getCatverId();
    }
}
