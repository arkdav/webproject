package com.marpen.shop.controller;

import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserFacade userFacade;
    public UserController(UserFacade userFacade) {

        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        UserDto userDto = userFacade.getUserInformation(getUserLogin());
        model.addAttribute("currentUser", userDto);
        return "userdata";
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
