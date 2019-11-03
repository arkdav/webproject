package com.marpen.shop.service;

import com.marpen.shop.model.Image;

import java.util.List;

public interface ImageService {

    Image getImageById(int image_id);

    List<Image> getImageListByProductId(int product_id);

    Image getImageByProductId(int product_id);
}
