package com.marpen.shop.model;


import javax.persistence.*;

@Entity
@Table(name = "catalogversion")
public class CatalogVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "catver_id")
    private int catverId;

    @Column(name = "name")
    private String name;

    public CatalogVersion() {
    }

    public CatalogVersion(int catverId, String name) {
        this.catverId =catverId;
        this.name = name;
    }

    public Integer getCatverId() {
        return catverId;
    }

    public void setCatverId(int catverId) {
        this.catverId = catverId;
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
                "catverId=" + catverId +
                ", name='" + name + '\'' +
                '}';
    }
}
