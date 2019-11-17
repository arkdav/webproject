package com.marpen.shop.dto;


public class ProductDto {

    private int productId;
    private String name;
    private String description;
    private String imageLink;
    private Double price;

    public ProductDto() {
    }

    public ProductDto(int productId, String name, String description, String imageLink, Double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.price = price;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
