package com.marpen.shop.dto;


public class ProductDto {

    private int productId;
    private String name;
    private String information;
    private String type;
    private Double price;
    private String link;

    public ProductDto(){}

    public ProductDto(int productId, String name, String information, String type, Double price, String link) {
        this.productId = productId;
        this.name=name;
        this.information=information;
        this.type=type;
        this.price=price;
        this.link=link;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLink() {return link;}

    public void setLink(String link) { this.link=link; }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price + '\''+
                ", link="+ link +
                '}';
    }
}
