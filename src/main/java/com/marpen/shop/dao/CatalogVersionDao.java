package com.marpen.shop.dao;

import com.marpen.shop.model.CatalogVersion;

public interface CatalogVersionDao extends GenericDao<CatalogVersion> {

    int getCatalogVersionIdByName(String name);
}
