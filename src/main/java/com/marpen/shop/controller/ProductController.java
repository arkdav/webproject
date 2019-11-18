package com.marpen.shop.controller;

import com.marpen.shop.facade.ProductFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.jsp.PageContext;


@Controller
public class ProductController {

    private ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {

        this.productFacade = productFacade;
    }

    @RequestMapping(value = "/catalog", method = {RequestMethod.GET})
    public String getProductsList(@RequestParam(value = "pageid", required = false, defaultValue = "1") Integer pageid,
                                  @RequestParam(value = "searchString", required = false) String searchName,
                                  Model model) {
        int productsPerPage = 6;
        if (pageid != 1) {
            pageid = (pageid - 1) * productsPerPage + 1;
        }
        model.addAttribute("productsList", this.productFacade.getCatalogList(searchName, pageid, productsPerPage));
        model.addAttribute("pagesList", this.productFacade.getIdList(searchName, productsPerPage));
        return "catalog";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }


    @RequestMapping(value = "/productdata/{product_id}", method = RequestMethod.GET)
    public String productData(@PathVariable("product_id") int productId,
                              Model model) {
        model.addAttribute("productinf", this.productFacade.getProductById(productId));
        return "productdata";
    }

    @RequestMapping(value = "/businessdata", method = RequestMethod.GET)
    public String getBusinessPage(Model model) {
        model.addAttribute("productsList", this.productFacade.getProductsList());
        return "businessdata";
    }

}

