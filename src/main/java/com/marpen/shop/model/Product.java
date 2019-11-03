package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;


@Entity
@Table(name = "products")
@Proxy(lazy = false)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private int product_id;

    @Column(name = "name")
    private String name;

    @Column(name = "information")
    private String information;

    @Column(name = "type")
    private String type;

    @Column(name = "catver_id")
    private int catver_id;


    public Product() {
    }

    public Product(int product_id, String name, String information, String type, int catver_id) {
        this.product_id = product_id;
        this.name = name;
        this.information = information;
        this.type = type;
        this.catver_id = catver_id;
    }

    public Integer getProduct_id() {

        return product_id;
    }

    public void setProduct_id(int product_id) {

        this.product_id = product_id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getInformation() {

        return information;
    }

    public void setInformation(String info) {

        this.information = info;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public Integer getCatver_id() {
        return catver_id;
    }

    public void setCatver_id(Integer catver_id) {

        this.catver_id = catver_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", type='" + type + '\'' +
                ", catver_id=" + catver_id +
                '}';
    }

}
