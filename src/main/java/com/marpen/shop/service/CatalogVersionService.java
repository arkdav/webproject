package com.marpen.shop.service;

import com.marpen.shop.model.CatalogVersion;

public interface CatalogVersionService {

    CatalogVersion getCatalogVersionById(int catVerId);

    int getCatalogVersionIdByName(String name);

    String getCatalogVersionNameById(int catVerId);

}
