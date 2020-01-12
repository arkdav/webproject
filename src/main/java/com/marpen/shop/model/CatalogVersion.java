package com.marpen.shop.model;


import javax.persistence.*;
import java.util.Objects;

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

    public int getCatverId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogVersion that = (CatalogVersion) o;
        return catverId == that.catverId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catverId, name);
    }

    @Override
    public String toString() {
        return "CatalogVersion{" +
                "catverId=" + catverId +
                ", name='" + name + '\'' +
                '}';
    }
}
