package com.marpen.shop.controller;

import com.marpen.shop.model.User;
import com.marpen.shop.service.SecurityService;
import com.marpen.shop.service.UserService;
import com.marpen.shop.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class UserController {

    UserController(){}
    private UserValidator userValidator;
    private SecurityService securityService;
    private UserService userService;

    public UserController(UserValidator userValidator, SecurityService securityService, UserService userService) {
        this.userValidator = userValidator;
        this.securityService=securityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        return "redirect:/catalog";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLoginPage(Model model) {
        return "login";
    }

}
