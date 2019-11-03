package com.marpen.shop.model;


import javax.persistence.*;

@Entity
@Table(name = "catalogversion")
public class CatalogVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "catver_id")
    private int catver_id;

    @Column(name = "name")
    private String name;

    public CatalogVersion() {
    }

    public CatalogVersion(int catver_id, String name) {
        this.catver_id=catver_id;
        this.name = name;
    }

    public Integer getCatver_id() {
        return catver_id;
    }

    public void setCatver_id(int catver_id) {
        this.catver_id = catver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CatalogVersion{" +
                "catver_id=" + catver_id +
                ", name='" + name + '\'' +
                '}';
    }
}
