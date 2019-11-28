package com.marpen.shop.controller;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.validator.BusinessProductValidator;
import com.marpen.shop.validator.ProductCreationValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class ProductController {

    private ProductFacade productFacade;
    private BusinessProductValidator businessProductValidator;
    private ProductCreationValidator productCreationValidator;
    private UserFacade userFacade;

    public ProductController(ProductFacade productFacade,
                             BusinessProductValidator businessProductValidator,
                             ProductCreationValidator productCreationValidator,
                             UserFacade userFacade) {

        this.productFacade = productFacade;
        this.businessProductValidator = businessProductValidator;
        this.productCreationValidator = productCreationValidator;
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String getProductsList(@RequestParam(value = "pageid", required = false, defaultValue = "1") Integer pageid,
                                  @RequestParam(value = "searchString", required = false) String searchName,
                                  Model model) {
        int productsPerPage = 6;
        if (pageid != 1) {
            pageid = (pageid - 1) * productsPerPage + 1;
        }
        if (searchName != null) {
            model.addAttribute("search", searchName);
        }
        model.addAttribute("productsList", this.productFacade.getCatalogList(searchName, pageid, productsPerPage));
        model.addAttribute("pagesList", this.productFacade.getCatalogPagesList(searchName, productsPerPage));
        return "catalog";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getCatalog() {
        return "redirect:/catalog";
    }

    @RequestMapping(value = "/productdata/{product_id}", method = RequestMethod.GET)
    public String productData(@PathVariable("product_id") int productId,
                              Model model) {
        model.addAttribute("productinf", this.productFacade.getProductById(productId));
        return "productdata";
    }

    @RequestMapping(value = {"/businessdata"}, method = RequestMethod.GET)
    public String getBusinessPage(Model model) {
        model.addAttribute("businessProductsList", this.productFacade.getProductsListByUserId(getUserId()));
        return "businessdata";
    }

    @RequestMapping(value = "/business/delete", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam(value = "productId") int productId) {
        productFacade.deleteProduct(productId);
        return "redirect:/businessdata";
    }

    @RequestMapping(value = "/business/update", method = RequestMethod.GET)
    public String createProduct(@RequestParam(value = "productId") int productId,
                                Model model) {
        model.addAttribute("businessProduct", this.productFacade.getBusinessProductDtoById(productId));
        model.addAttribute("businessDataForm", new BusinessProductDto());
        return "productupdate";
    }

    @RequestMapping(value = "/business/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("businessDataForm") BusinessProductDto businessProductDto,
                                BindingResult bindingResult) {
        businessProductValidator.validate(businessProductDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "productupdate";
        }
        businessProductDto.setUserId(getUserId());
        productFacade.updateProduct(businessProductDto);
        return "redirect:/businessdata";
    }

    @RequestMapping(value = "/business/create", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("creationBusinessDataForm", new BusinessProductCreationDto());
        return "productcreation";
    }

    @RequestMapping(value = "/business/create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("creationBusinessDataForm")
                                        BusinessProductCreationDto businessProductCreationDto,
                                BindingResult bindingResult) {
        productCreationValidator.validate(businessProductCreationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "productcreation";
        }
        MultipartFile file = businessProductCreationDto.getImage();
        try {
            String filename = System.getenv("CATALINA_HOME") + "\\webapps\\images\\" + file.getOriginalFilename();
            file.transferTo(new File(filename));
            businessProductCreationDto.setUserId(getUserId());
            productFacade.createProduct(businessProductCreationDto);
        } catch (IOException e) {
            return "productcreation";
        }
        return "redirect:/businessdata";
    }

    private int getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.getUserInformation(auth.getName()).getUserId();
    }

}

