package com.marpen.shop.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {

    @JsonProperty
    private int productId;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String imageLink;
    @JsonProperty
    private String price;

    public ProductDto() {
    }

    public ProductDto(int productId, String name, String description, String imageLink, String price) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
