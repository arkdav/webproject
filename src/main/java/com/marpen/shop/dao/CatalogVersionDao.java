package com.marpen.shop.dao;

import com.marpen.shop.model.CatalogVersion;

public interface CatalogVersionDao extends GenericDao<CatalogVersion> {

    CatalogVersion getCatalogVersionById(int catVerId);

    int getCatalogVersionIdByName(String name);
}
