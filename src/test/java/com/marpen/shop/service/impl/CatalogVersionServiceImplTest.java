package com.marpen.shop.service.impl;

import com.marpen.shop.dao.impl.CatalogVersionDaoImpl;
import com.marpen.shop.model.CatalogVersion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatalogVersionServiceImplTest {

    @Mock
    private CatalogVersionDaoImpl catalogVersionDao;

    @InjectMocks
    private CatalogVersionServiceImpl catalogVersionService;

    private CatalogVersion catalogVersion1;

    @Before
    public void setUp() {
        catalogVersion1 = new CatalogVersion(1, "past");
    }

    @Test
    public void getCatalogVersionIdByName() {
        Mockito.when(catalogVersionDao.getCatalogVersionByName("past")).thenReturn(catalogVersion1);
        int actual = catalogVersionService.getCatalogVersionIdByName("past");
        assertEquals(catalogVersion1.getCatverId(), actual);
    }

    @Test
    public void getCatalogVersionNameById() {
        Mockito.when(catalogVersionDao.get(1)).thenReturn(catalogVersion1);
        String actual = catalogVersionService.getCatalogVersionNameById(1);
        assertEquals(catalogVersion1.getName(), actual);
    }
}