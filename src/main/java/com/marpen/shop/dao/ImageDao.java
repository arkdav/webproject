package com.marpen.shop.dao;

import com.marpen.shop.model.Image;

import java.util.List;

public interface ImageDao {

    Image getImageById(int imageId);

    List<Image> getImageListByProductId(int productId);

    Image getImageByProductId(int productId);

}
