package com.marpen.shop.dto;

import org.springframework.web.multipart.MultipartFile;

public class BusinessProductCreationDto {

    private String name;
    private String description;
    private String price;
    private String catalogVersion;
    private MultipartFile image;
    private int userId;

    public BusinessProductCreationDto() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCatalogVersion() { return catalogVersion; }

    public void setCatalogVersion(String catalogVersion) { this.catalogVersion = catalogVersion; }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
