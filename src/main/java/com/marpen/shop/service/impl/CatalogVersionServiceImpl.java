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
    public int getCatalogVersionIdByName(String name) {
        CatalogVersion catalogVersion=catalogVersionDao.getCatalogVersionByName(name);
        return catalogVersion==null ? 0 : catalogVersion.getCatverId();
    }

    @Override
    public String getCatalogVersionNameById(int catVerId) {
        return catalogVersionDao.get(catVerId).getName();
    }

}
