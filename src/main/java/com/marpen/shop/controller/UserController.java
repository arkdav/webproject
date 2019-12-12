package com.marpen.shop.controller;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.validator.RegistrationValidator;
import com.marpen.shop.validator.UserDataValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserFacade userFacade;
    private UserDataValidator userDataValidator;
    private RegistrationValidator registrationValidator;

    public UserController(UserFacade userFacade, UserDataValidator userDataValidator,
                          RegistrationValidator registrationValidator) {
        this.userFacade = userFacade;
        this.userDataValidator = userDataValidator;
        this.registrationValidator=registrationValidator;
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
        userFacade.update(getUserLogin(), userDataDto);
        return "redirect:/userdata";
    }

    @RequestMapping(value = "/adminusers", method = RequestMethod.GET)
    public String getAdminUsersPage(@RequestParam(name = "u", required = false) String role,
                                    Model model) {
        List<UserDto> users=new ArrayList<>();
        if(role.equals("customer")){
            users= this.userFacade.getUserListByRole("ROLE_CUSTOMER");
        } else if (role.equals("business")){
            users= this.userFacade.getUserListByRole("ROLE_BUSINESS_USER");
        }
        model.addAttribute("userList", users);
        model.addAttribute("role", role);
        return "adminusers";
    }

    @RequestMapping(value = "/adminusers/update", method = RequestMethod.GET)
    public String updateUserStatus(@RequestParam("user") String userLogin,
                                   @RequestParam("u") String role) {
        userFacade.changeUserStatus(userLogin);
        return "redirect:/adminusers?u="+role;
    }

    @RequestMapping(value = "/adminusers/create", method = RequestMethod.GET)
    public String getCreateBusinessUserPage(Model model) {
        model.addAttribute("businessUserCreationForm", new RegistrationDto());
        return "businessusercreation";
    }

    @RequestMapping(value = "/adminusers/create", method = RequestMethod.POST)
    public String createBusinessUser(@ModelAttribute("businessUserCreationForm")RegistrationDto registrationDto,
                                    BindingResult bindingResult){
        registrationValidator.validate(registrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "businessusercreation";
        }
        userFacade.saveBusinessUser(registrationDto);
        return "redirect:/adminusers?u=business";
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
