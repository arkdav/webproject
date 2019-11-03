package com.marpen.shop.dto;

public class PageDto {

    private int page_id;

    public PageDto() {
    }

    public PageDto(int page_id) {
        this.page_id=page_id;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }
}
