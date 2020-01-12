package com.marpen.shop.facade.impl;

import com.marpen.shop.model.Product;
import com.marpen.shop.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductFacadeImplTest {
    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductFacadeImpl productFacade;

    private static final String[] PRODUCT_IDS = {"1", "2"};
    private static final int ID_FIRST = 1;
    private static final int ID_SECOND = 2;
    private static final boolean isTrue = true;
    private static final boolean isFalse = false;
    private static final String EMPTY = "";
    private static final int CAT_VER_ID = 1;
    private static final Product PRODUCT_FIRST = new Product(ID_FIRST, EMPTY, EMPTY, EMPTY, CAT_VER_ID, EMPTY);
    private static final Product PRODUCT_SECOND = new Product(ID_SECOND, EMPTY, EMPTY, EMPTY, CAT_VER_ID, EMPTY);
    private static final Product PRODUCT_NULL = null;

    @Test
    public void changeProductsVersion_True() {
        Mockito.when(productService.getProductById(ID_FIRST)).thenReturn(PRODUCT_FIRST);
        Mockito.when(productService.getProductById(ID_SECOND)).thenReturn(PRODUCT_SECOND);
        boolean actual = productFacade.changeProductsVersion(PRODUCT_IDS);
        assertEquals(isTrue, actual);
    }

    @Test
    public void changeProductsVersion_False() {
        Mockito.when(productService.getProductById(ID_FIRST)).thenReturn(PRODUCT_FIRST);
        Mockito.when(productService.getProductById(ID_SECOND)).thenReturn(PRODUCT_NULL);
        boolean actual = productFacade.changeProductsVersion(PRODUCT_IDS);
        assertEquals(isFalse, actual);
    }

    @Test
    public void deleteProducts_True() {
        Mockito.when(productService.getProductById(ID_FIRST)).thenReturn(PRODUCT_FIRST);
        Mockito.when(productService.getProductById(ID_SECOND)).thenReturn(PRODUCT_SECOND);
        boolean actual = productFacade.deleteProducts(PRODUCT_IDS);
        assertEquals(isTrue, actual);
    }

    @Test
    public void deleteProducts_False() {
        Mockito.when(productService.getProductById(ID_FIRST)).thenReturn(PRODUCT_NULL);
        Mockito.when(productService.getProductById(ID_SECOND)).thenReturn(PRODUCT_NULL);
        boolean actual = productFacade.deleteProducts(PRODUCT_IDS);
        assertEquals(isFalse, actual);
    }

}