package com.marpen.shop.dto;

public class ImageDto {

    private int image_id;
    private String link;

    public ImageDto(){
    }

    public ImageDto(int image_id, String link) {
        this.image_id = image_id;
        this.link = link;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
