package com.marpen.shop.service;

import com.marpen.shop.model.Image;

import java.util.List;

public interface ImageService {

    Image getImageById(int imageId);

    List<Image> getImageListByProductId(int productId);

    Image getImageByProductId(int productId);
}
