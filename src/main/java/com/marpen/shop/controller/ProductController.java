package com.marpen.shop.controller;

import com.marpen.shop.facade.ProductFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

   private ProductFacade productFacade;

   public ProductController(ProductFacade productFacade) {

       this.productFacade = productFacade;
    }

    @RequestMapping(value="/catalog", method = {RequestMethod.GET})
    public String getProductsList(@RequestParam(value="pageid", required=false,defaultValue = "1") Integer pageid,
                                  @RequestParam(name="searchString", required=false) String searchName,
                                  Model model) {
        int productsPerPage = 6;
        if(pageid!=1) {
            pageid = (pageid - 1) * productsPerPage + 1;
        }
        if(searchName==null){
            model.addAttribute("productsList", this.productFacade.getCatalogList(pageid, productsPerPage));
            model.addAttribute("pagesList", this.productFacade.getIdList(productsPerPage));
        } else{
            model.addAttribute("search", searchName);
            model.addAttribute("productsList", this.productFacade.getCatalogListSearch(searchName, pageid, productsPerPage));
            model.addAttribute("pagesList", this.productFacade.getIdListSearch(searchName,productsPerPage));
        }
        return "catalog";
    }

    @RequestMapping(value="/home", method = {RequestMethod.GET})
    public String getHome(){
       return "home";
    }

//    @RequestMapping(value="/catalog/search", method = {RequestMethod.GET})
//    public String getSearchList(@RequestParam(value="pageid", required=false,defaultValue = "1") Integer pageid,
//                                @RequestParam(name="searchString", required=true) String searchName,
//                                Model model) {
//        int productsPerPage = 6;
//        if(pageid!=1) {
//            pageid = (pageid - 1) * productsPerPage + 1;
//        }
//        model.addAttribute("productsList", this.productFacadeImpl.getCatalogListSearch(searchName, pageid, productsPerPage));
//        model.addAttribute("pagesList", this.productFacadeImpl.getIdListSearch(searchName, productsPerPage));
//        model.addAttribute("search", searchName);
//        return "catalog";
//    }

    @RequestMapping(value = "/productdata/{product_id}", method = RequestMethod.GET)
    public String productData(@PathVariable("product_id")int product_id, Model model) {
        model.addAttribute("productinf", this.productFacade.getProductById(product_id));
        model.addAttribute("images", this.productFacade.getImageListByProductId(product_id));
        return "productdata";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String addToBasket(Model model) {
        return "basket";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

}
