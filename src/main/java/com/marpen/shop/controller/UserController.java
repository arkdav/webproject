package com.marpen.shop.controller;

import com.marpen.shop.dto.BasketDto;
import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.BasketFacade;
import com.marpen.shop.facade.SecurityFacade;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.validator.RegistrationValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class UserController {

    private UserFacade userFacade;
    private RegistrationValidator registrationValidator;
    private SecurityFacade securityFacade;
    private BasketFacade basketFacade;

    public UserController(UserFacade userFacade,
                          RegistrationValidator registrationValidator,
                          SecurityFacade securityFacade,
                          BasketFacade basketFacade){

        this.userFacade=userFacade;
        this.registrationValidator = registrationValidator;
        this.securityFacade=securityFacade;
        this.basketFacade=basketFacade;
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
        UserDto userDto = userFacade.save(registrationDto);
        securityFacade.autologin(registrationDto.getLogin(), registrationDto.getPassword());
        model.addAttribute("currentUser",userDto);
        return "redirect:/catalog";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        return "login";
    }

//    @ModelAttribute("currentUser")
//    public UserDto createUserDto(){
//        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//        UserDto user=new UserDto();
//        if(auth==null || (auth instanceof AnonymousAuthenticationToken)||!auth.isAuthenticated()){
//        } else {
//            String login = auth.getName(); //get logged in username
//            user = userFacade.getUserInformation(login);
//        }
//        return user;
//    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public String getUserPage(Model model,
                              String logout) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
        UserDto userDto = userFacade.getUserInformation(login);
        model.addAttribute("currentUser", userDto);
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "userdata";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String addToBasket(@ModelAttribute("currentUser")UserDto userDto,
                              @RequestParam(value="product_id", required=true) int productId) {
        basketFacade.addProductToBasket(userDto.getUserId(),productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String giveBasket(@ModelAttribute("currentUser")UserDto userDto,
                             Model model) {
        BasketDto basketDto=basketFacade.getBasketByUserId(userDto.getUserId());
        model.addAttribute("basketList", basketDto);
        return "basket";
    }

}
