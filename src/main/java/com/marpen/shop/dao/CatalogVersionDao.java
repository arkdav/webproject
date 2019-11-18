package com.marpen.shop.dao;

import com.marpen.shop.model.CatalogVersion;

public interface CatalogVersionDao {

    CatalogVersion getCatalogVersionById(int catVerId);

    int getCatalogVersionIdByName(String name);
}
