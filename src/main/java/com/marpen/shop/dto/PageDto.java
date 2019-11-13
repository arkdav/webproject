package com.marpen.shop.dto;

public class PageDto {

    private int pageId;

    public PageDto() {
    }

    public PageDto(int pageId) {
        this.pageId= pageId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
