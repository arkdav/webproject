package com.marpen.shop.controller;

import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.validator.UserDataValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserFacade userFacade;
    private UserDataValidator userDataValidator;

    public UserController(UserFacade userFacade, UserDataValidator userDataValidator) {
        this.userFacade = userFacade;
        this.userDataValidator = userDataValidator;
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        UserDto userDto = userFacade.getUserInformation(getUserLogin());
        model.addAttribute("currentUser", userDto);
        model.addAttribute("userDataForm", new UserDataDto());
        return "userdata";
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.POST)
    public String updateUserData(@ModelAttribute("userDataForm") UserDataDto userDataDto,
                                 BindingResult bindingResult) {
        userDataValidator.validate(userDataDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "userdata";
        }
        userFacade.update(getUserId(), userDataDto);
        return "redirect:/userdata";
    }

    @RequestMapping(value = "/admindata", method = RequestMethod.GET)
    public String getAdminPage() {
        return "admindata";
    }

    private int getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.getUserInformation(auth.getName()).getUserId();
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
