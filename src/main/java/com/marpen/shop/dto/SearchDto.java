package com.marpen.shop.dto;

public class SearchDto {

    private String productsPerPage;

    private String pageid;

    private String searchName;

    public SearchDto() {
    }

    public SearchDto(String productsPerPage, String pageid, String searchName) {
        this.productsPerPage = productsPerPage;
        this.pageid = pageid;
        this.searchName = searchName;
    }

    public String getProductsPerPage() {
        return productsPerPage;
    }

    public void setProductsPerPage(String productsPerPage) {
        this.productsPerPage = productsPerPage;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

}
