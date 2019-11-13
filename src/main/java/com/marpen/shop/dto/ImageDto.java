package com.marpen.shop.dto;

public class ImageDto {

    private int imageId;
    private String link;

    public ImageDto(){
    }

    public ImageDto(int imageId, String link) {
        this.imageId = imageId;
        this.link = link;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int image_id) {
        this.imageId = imageId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
