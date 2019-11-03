package com.marpen.shop.model;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="image_id")
    private int image_id;

    @Column(name="link")
    private String link;

    @Column(name="product_id")
    private int product_id;

    public Image() {
    }

    public Image(int image_id, String link, int product_id) {
        this.image_id = image_id;
        this.link = link;
        this.product_id = product_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
                "image_id=" + image_id +
                ", link='" + link + '\'' +
                ", product_id=" + product_id +
                '}';
    }
}
