package com.marpen.shop.service.impl;

import com.marpen.shop.dao.ImageDao;
import com.marpen.shop.model.Image;
import com.marpen.shop.service.ImageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ImageServiceImpl implements ImageService {

    private ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao){

        this.imageDao=imageDao;
    }

    @Override
    public Image getImageById(int imageId) {
        return this.imageDao.getImageById(imageId);
    }

    @Override
    public List<Image> getImageListByProductId(int productId) {
        return this.imageDao.getImageListByProductId(productId);
    }

    @Override
    public Image getImageByProductId(int productId) {
        return this.imageDao.getImageByProductId(productId);
    }
}
