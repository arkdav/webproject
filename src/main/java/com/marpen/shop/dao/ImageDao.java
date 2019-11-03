package com.marpen.shop.dao;

import com.marpen.shop.model.Image;

import java.util.List;

public interface ImageDao {

    Image getImageById(int image_id);

    List<Image> getImageListByProductId(int product_id);

    Image getImageByProductId(int product_id);

}
