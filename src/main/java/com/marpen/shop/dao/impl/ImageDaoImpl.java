package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.ImageDao;
import com.marpen.shop.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ImageDaoImpl implements ImageDao {

    private SessionFactory sessionFactory;

    public ImageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
        sessionFactory.openSession();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Image getImageById(int image_id){

        return  (Image) currentSession().load(Image.class, image_id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Image> getImageListByProductId(int product_id){
        String sql="select * from images where product_id like :product_id";
        List<Image> images=currentSession().createSQLQuery(sql).addEntity(Image.class).setParameter("product_id", product_id).list();
        return images;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Image getImageByProductId(int product_id){
        String sql="select * from images where product_id like :product_id limit 1";
        List<Image> images=currentSession().createSQLQuery(sql).addEntity(Image.class).setParameter("product_id", product_id).list();
        return  images.get(0);
    }

}
