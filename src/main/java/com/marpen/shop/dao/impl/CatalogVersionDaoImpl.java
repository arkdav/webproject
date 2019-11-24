package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.CatalogVersionDao;
import com.marpen.shop.model.CatalogVersion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CatalogVersionDaoImpl extends GenericDaoImpl<CatalogVersion> implements CatalogVersionDao {

    private Session session;

    public CatalogVersionDaoImpl(SessionFactory sessionFactory) {
        if (super.getSessionFactory() == null) {
            super.setSessionFactory(sessionFactory);

        }
        this.session = super.getSession();
    }

    private Session currentSession() {
        return this.session;
    }

    @Override
    public int getCatalogVersionIdByName(String name) {
        String sql = "Select * from catalogversion where name like :name";
        List<CatalogVersion> catalogVersions = currentSession().createSQLQuery(sql).addEntity(CatalogVersion.class).setParameter("name", name).list();
        return catalogVersions.isEmpty() ? 0 : catalogVersions.get(0).getCatverId();
    }
}
