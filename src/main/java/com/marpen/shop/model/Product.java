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
    private int productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "catver_id")
    private int catverId;


    public Product() {
    }

    public Product(int productId, String name, String description, String type, int catverId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.catverId = catverId;
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

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public Integer getCatverId() {
        return catverId;
    }

    public void setCatverId(Integer catverId) {

        this.catverId = catverId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", information='" + description + '\'' +
                ", type='" + type + '\'' +
                ", catverId=" + catverId +
                '}';
    }

}
