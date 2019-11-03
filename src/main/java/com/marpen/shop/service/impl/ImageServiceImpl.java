package com.marpen.shop.service.impl;

import com.marpen.shop.dao.ImageDao;
import com.marpen.shop.model.Image;
import com.marpen.shop.service.ImageService;

import java.util.List;

public class ImageServiceImpl implements ImageService {

    private ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao){

        this.imageDao=imageDao;
    }

    @Override
    public Image getImageById(int image_id) {
        return this.imageDao.getImageById(image_id);
    }

    @Override
    public List<Image> getImageListByProductId(int product_id) {
        return this.imageDao.getImageListByProductId(product_id);
    }

    @Override
    public Image getImageByProductId(int product_id) {
        return this.imageDao.getImageByProductId(product_id);
    }
}
