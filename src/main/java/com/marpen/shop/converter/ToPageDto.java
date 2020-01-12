package com.marpen.shop.converter;

import com.marpen.shop.dto.PageDto;

import java.util.ArrayList;
import java.util.List;

public class ToPageDto {

    public List<PageDto> convert(int amountOfProducts, int productsPerPage) {
        int pagesNumber = amountOfProducts % productsPerPage == 0 ? amountOfProducts / productsPerPage : amountOfProducts / productsPerPage + 1;
        List<PageDto> list = new ArrayList<>(pagesNumber);
        for (int i = 0; i < pagesNumber; i++) {
            PageDto pr = new PageDto(i + 1);
            list.add(pr);
        }
        return list;
    }
}
