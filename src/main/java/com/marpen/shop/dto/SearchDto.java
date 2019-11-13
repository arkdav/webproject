package com.marpen.shop.dto;

public class SearchDto {

    private String productsPerPage;

    private String pageId;

    private String searchName;

    public SearchDto() {
    }

    public SearchDto(String productsPerPage, String pageId, String searchName) {
        this.productsPerPage = productsPerPage;
        this.pageId = pageId;
        this.searchName = searchName;
    }

    public String getProductsPerPage() {
        return productsPerPage;
    }

    public void setProductsPerPage(String productsPerPage) {
        this.productsPerPage = productsPerPage;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

}
