package com.marpen.shop.service.impl;

import com.marpen.shop.dao.ImageDao;
import com.marpen.shop.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

    @Mock
    private ImageDao imageDao;

    @InjectMocks
    private ImageServiceImpl imageService;

    private Image image;

    @Before
    public void setUp() {
        image = new Image (1, "link", 12);
    }

    @Test
    public void getImageById() {
        Mockito.when(imageDao.getImageById(1)).thenReturn(image);
        Image actual = imageService.getImageById(1);
        assertEquals(image, actual);
    }

    @Test
    public void getImageListByProductId() {
        List<Image> listImg=new ArrayList<>();
        listImg.add(image);
        listImg.add(image);
        Mockito.when(imageDao.getImageListByProductId(12)).thenReturn(listImg);
        List<Image> imgs=imageService.getImageListByProductId(12);
        assertEquals(listImg, imgs);
    }

    @Test
    public void getImageByProductId() {

    }
}