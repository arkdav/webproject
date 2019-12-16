package com.marpen.shop.controller;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.validator.BusinessProductValidator;
import com.marpen.shop.validator.ProductCreationValidator;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class ProductController {

    private ProductFacade productFacade;
    private BusinessProductValidator businessProductValidator;
    private ProductCreationValidator productCreationValidator;

    public ProductController(ProductFacade productFacade,
                             BusinessProductValidator businessProductValidator,
                             ProductCreationValidator productCreationValidator) {

        this.productFacade = productFacade;
        this.businessProductValidator = businessProductValidator;
        this.productCreationValidator = productCreationValidator;
    }


    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String getProductsList(@RequestParam(value = "pageid", required = false, defaultValue = "1") Integer pageid,
                                  Model model) {
        int productsPerPage = 6;
        if (pageid != 1) {
            pageid = (pageid - 1) * productsPerPage + 1;
        }
        model.addAttribute("productsList", this.productFacade.getCatalogList(null, pageid, productsPerPage));
        model.addAttribute("pagesList", this.productFacade.getCatalogPagesList(null, productsPerPage));
        return "catalog";
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ProductDto> getProductsList(@RequestParam(value = "searchString", required = false) String searchName,
                                            @RequestParam(value = "pageid", required = false, defaultValue = "1") Integer pageId,
                                            @RequestParam(value="perpage", required=false, defaultValue="6") Integer productsPerPage) {
        if (pageId != 1) {
            pageId = (pageId - 1) * productsPerPage + 1;
        }
        return this.productFacade.getCatalogList(searchName, pageId, productsPerPage);
    }

    @RequestMapping(value = "/pages", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PageDto> getProductsPages(@RequestParam(value = "searchString", required = false) String searchName,
                                          @RequestParam(value="perpage", required=false, defaultValue="6") Integer productsPerPage) {
        return this.productFacade.getCatalogPagesList(searchName, productsPerPage);
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
        model.addAttribute("businessProductsList", this.productFacade.getProductsListByUserLogin(getUserLogin()));
        return "businessdata";
    }

    @RequestMapping(value = "/businessdata/delete", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam(value = "productId") int productId) {
        productFacade.deleteProduct(productId);
        return "redirect:/businessdata";
    }

    @RequestMapping(value = "/businessdata/update", method = RequestMethod.GET)
    public String createProduct(@RequestParam(value = "productId") int productId,
                                Model model) {
        model.addAttribute("businessProduct", this.productFacade.getBusinessProductDtoById(productId));
        model.addAttribute("businessDataForm", new BusinessProductDto());
        return "productupdate";
    }

    @RequestMapping(value = "/businessdata/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("businessDataForm") BusinessProductDto businessProductDto,
                                BindingResult bindingResult) {
        businessProductValidator.validate(businessProductDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "productupdate";
        }
        productFacade.updateProduct(getUserLogin(),businessProductDto);
        return "redirect:/businessdata";
    }

    @RequestMapping(value = "/businessdata/create", method = RequestMethod.GET)
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
            String filename = System.getenv("CATALINA_HOME") + "\\webapps\\webproject\\resources\\images\\" + file.getOriginalFilename();
            file.transferTo(new File(filename));
             } catch (IOException e) {
            return "productcreation";
        }
        productFacade.createProduct(getUserLogin(), businessProductCreationDto);
        return "redirect:/businessdata";
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

