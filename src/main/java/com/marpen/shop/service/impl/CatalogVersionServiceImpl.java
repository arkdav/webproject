package com.marpen.shop.service.impl;

import com.marpen.shop.dao.CatalogVersionDao;
import com.marpen.shop.model.CatalogVersion;
import com.marpen.shop.service.CatalogVersionService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CatalogVersionServiceImpl implements CatalogVersionService {

    private CatalogVersionDao catalogVersionDao;

    public CatalogVersionServiceImpl(CatalogVersionDao catalogVersionDao){
        this.catalogVersionDao=catalogVersionDao;
    }

    @Override
    public CatalogVersion getCatalogVersionById(int catVerId) {
        return catalogVersionDao.getCatalogVersionById(catVerId);
    }

    @Override
    public int getCatalogVersionIdByName(String name) {
        return catalogVersionDao.getCatalogVersionIdByName(name);
    }

    @Override
    public String getCatalogVersionNameById(int catVerId) {
        return catalogVersionDao.getCatalogVersionById(catVerId).getName();
    }

}
