package com.marpen.shop.controller;

import com.marpen.shop.dto.BusinessProductCreationDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.validator.BusinessProductValidator;
import com.marpen.shop.validator.ProductCreationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/catalog", method = {RequestMethod.GET})
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
        model.addAttribute("pagesList", this.productFacade.getCatalogIdList(searchName, productsPerPage));
        return "catalog";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String toHome() {
        return "redirect:/home";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

    @RequestMapping(value = "/productdata/{product_id}", method = RequestMethod.GET)
    public String productData(@PathVariable("product_id") int productId,
                              Model model) {
        model.addAttribute("productinf", this.productFacade.getProductById(productId));
        return "productdata";
    }

    @RequestMapping(value = {"/businessdata"}, method = RequestMethod.GET)
    public String getBusinessPage(@RequestParam(value = "productId", required = false) Integer productId,
                                  Model model) {
        List<Integer> productIdList = productFacade.getIdList();
        if (productId == null) {
            productId = productIdList.get(0);
        }
        model.addAttribute("businessProduct", this.productFacade.getBusinessProductDtoById(productId));
        model.addAttribute("businessDataForm", new BusinessProductDto());
        model.addAttribute("productIdList", productIdList);
        return "businessdata";
    }

    @RequestMapping(value = "/business/delete", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam(value = "productId") int productId) {
        productFacade.deleteProduct(productId);
        return "redirect:/businessdata";
    }

    @RequestMapping(value = "/business/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("businessDataForm") BusinessProductDto businessProductDto,
                                BindingResult bindingResult) {
        businessProductValidator.validate(businessProductDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            productFacade.updateProduct(businessProductDto);
        }
        return "redirect:/businessdata?productId="+businessProductDto.getProductId();
    }

    @RequestMapping(value = "/business/create", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("creationBusinessDataForm", new BusinessProductCreationDto());
        return "productcreation";
    }

    @RequestMapping(value = "/business/create", method = RequestMethod.POST)
    public String createProduct(HttpServletRequest request,
                                @ModelAttribute("creationBusinessDataForm")
                                        BusinessProductCreationDto businessProductCreationDto,
                                BindingResult bindingResult) {
        productCreationValidator.validate(businessProductCreationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "productcreation";
        }
        String path = request.getContextPath();
        MultipartFile file = businessProductCreationDto.getImage();
        try {
            String filename = System.getenv("CATALINA_HOME") + "\\webapps\\" + path.substring(1) +
                    "\\images\\" + file.getOriginalFilename(); //папка развертывания сервера
//            String filename = System.getenv("CATALINA_HOME") + "\\images\\" + file.getOriginalFilename();
            file.transferTo(new File(filename));
            //String absoluteDiskPath = request.getServletContext().getRealPath(/images);
            //String uploadsFolder = "/var/webproject_war/uploads";
            //File fileStr=new File(uploadsFolder, file.getOriginalFilename());
            productFacade.createProduct(businessProductCreationDto);
        } catch (IOException e) {
            return "productcreation";
        }
        return "redirect:/businessdata";
    }

}

