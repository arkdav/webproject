package com.marpen.shop.service;

public interface CatalogVersionService {

    int getCatalogVersionIdByName(String name);

    String getCatalogVersionNameById(int catVerId);
}
