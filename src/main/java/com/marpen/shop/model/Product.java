package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "products")
@Proxy(lazy = false)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imageurl")
    private String imageLink;

    @Column(name = "catver_id")
    private int catverId;

    @Column(name = "user_login")
    private String userLogin;

    public Product() {
    }

    public Product(int productId, String name, String description, String imageLink, int catverId, String userLogin) {
        this.productId=productId;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.catverId = catverId;
        this.userLogin = userLogin;
    }

    public Integer getProductId() {

        return productId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String info) {

        this.description = info;
    }

    public String getImageLink() {

        return imageLink;
    }

    public void setImageLink(String imageLink) {

        this.imageLink = imageLink;
    }

    public int getCatverId() {
        return catverId;
    }

    public void setCatverId(int catverId) {

        this.catverId = catverId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
}
