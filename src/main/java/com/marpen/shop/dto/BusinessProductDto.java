package com.marpen.shop.dto;

public class BusinessProductDto {

    private int productId;
    private String name;
    private String description;
    private String price;
    private String catalogVersion;
    private String imageLink;
    private String userLogin;

    public BusinessProductDto() {
    }

    public int getProductId() {
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
