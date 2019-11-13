package com.marpen.shop.model;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="image_id")
    private int imageId;

    @Column(name="link")
    private String link;

    @Column(name="product_id")
    private int productId;

    public Image() {
    }

    public Image(int imageId, String link, int productId) {
        this.imageId = imageId;
        this.link = link;
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", link='" + link + '\'' +
                ", productId=" + productId +
                '}';
    }
}
