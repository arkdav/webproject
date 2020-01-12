package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CatalogVersionDao;
import com.marpen.shop.model.CatalogVersion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CatalogVersionDaoImpl extends GenericDaoImpl<CatalogVersion> implements CatalogVersionDao {

    private CatalogVersionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public CatalogVersion getCatalogVersionByName(String name) {
        String sql = "Select * from catalogversion where name like :name";
        List<CatalogVersion> catalogVersions = currentSession().createSQLQuery(sql).addEntity(CatalogVersion.class).setParameter("name", name).list();
        return catalogVersions.isEmpty() ? null : catalogVersions.get(0);
    }
}
