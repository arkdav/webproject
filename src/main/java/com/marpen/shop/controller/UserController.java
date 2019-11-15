package com.marpen.shop.controller;

import com.marpen.shop.dto.BasketDto;
import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.BasketFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.facade.SecurityFacade;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.validator.RegistrationValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@SessionAttributes("currentUser")
public class UserController {

    private UserFacade userFacade;
    private RegistrationValidator registrationValidator;
    private SecurityFacade securityFacade;
    private BasketFacade basketFacade;
    private OrderFacade orderFacade;

    public UserController(UserFacade userFacade,
                          RegistrationValidator registrationValidator,
                          SecurityFacade securityFacade,
                          BasketFacade basketFacade,
                          OrderFacade orderFacade) {

        this.userFacade = userFacade;
        this.registrationValidator = registrationValidator;
        this.securityFacade = securityFacade;
        this.basketFacade = basketFacade;
        this.orderFacade = orderFacade;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new RegistrationDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") RegistrationDto registrationDto, BindingResult bindingResult, Model model) {
        registrationValidator.validate(registrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userFacade.save(registrationDto);
        securityFacade.autologin(registrationDto.getLogin(), registrationDto.getPassword());
        return "redirect:/catalog";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        return "login";
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public String getUserPage(Model model,
                              String logout) {
        UserDto userDto = userFacade.getUserInformation(getUserLogin());
        model.addAttribute("currentUser", userDto);
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "userdata";
    }

    @RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
    public String addToBasket(@RequestParam(value = "product_id", required = true) int productId) {
        basketFacade.addProductToBasket(getUserId(), productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String removeFromBasket(@RequestParam(value = "product_id", required = true) int productId) {
        basketFacade.removeProductFromBasket(getUserId(), productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String giveBasket(Model model) {
        BasketDto basketDto = basketFacade.getBasketByUserId(getUserId());
        model.addAttribute("basketList", basketDto);
        return "basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order() {
        orderFacade.addBasketToOrder(getUserId());
        basketFacade.removeBasket(getUserId());
        return "redirect:/userorders";
    }

    @RequestMapping(value = "/userorders", method = RequestMethod.GET)
    public String order(Model model) {
        //model.addAttribute("orderList");
        return "userorders";
    }

    private int getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.getUserInformation(auth.getName()).getUserId();
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
