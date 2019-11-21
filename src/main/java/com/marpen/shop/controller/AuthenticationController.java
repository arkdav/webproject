package com.marpen.shop.controller;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.facade.SecurityFacade;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.validator.RegistrationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    private UserFacade userFacade;
    private RegistrationValidator registrationValidator;
    private SecurityFacade securityFacade;

    public AuthenticationController(UserFacade userFacade, RegistrationValidator registrationValidator, SecurityFacade securityFacade) {
        this.userFacade = userFacade;
        this.registrationValidator = registrationValidator;
        this.securityFacade = securityFacade;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new RegistrationDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") RegistrationDto registrationDto,
                               BindingResult bindingResult) {
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
}
