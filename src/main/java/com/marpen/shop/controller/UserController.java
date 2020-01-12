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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminUsersPage(@RequestParam(name = "role", required = false, defaultValue = "") String role,
                                    @RequestParam(name = "status", required = false, defaultValue = "") String status,
                                    @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageId,
                                    @RequestParam(name="perpage", required = false, defaultValue = "7") Integer usersPerPage,
                                    Model model) {
        model.addAttribute("page", pageId);
        model.addAttribute("status", status);
        pageId = (pageId != 1) ? (pageId - 1) * usersPerPage + 1 : pageId;
        List<UserDto> users = this.userFacade.getUserListByRoleAndStatus(role, status, pageId, usersPerPage);
        model.addAttribute("userList", users);
        model.addAttribute("role", role);
        model.addAttribute("pagesList", this.userFacade.getUserPagesList(role, status, usersPerPage));
        return "admindata";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public String updateUser(@ModelAttribute("user") String login,
                             Model model) {
        model.addAttribute("user", userFacade.getUserInformation(login));
        model.addAttribute("userDataForm", new UserDataDto());
        return "userupdate";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userDataForm") UserDataDto userDataDto,
                             BindingResult bindingResult) {
        userDataValidator.validate(userDataDto, bindingResult);
        if (bindingResult.hasErrors()) {
           return "userupdate";
        }
        userFacade.update(userDataDto.getLogin(), userDataDto);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteUser(@RequestParam("stringArray") String usersLogins) {
        String [] logins = usersLogins.split(",");
        return userFacade.deleteUsers(logins) ? "true" : "";
    }


    @RequestMapping(value = "/admin/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public String changeUserStatus(@RequestParam("stringArray") String usersLogins) {
        String [] logins = usersLogins.split(",");
        return userFacade.changeUsersStatus(logins) ? "true" : "";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String getCreateBusinessUserPage(Model model) {
        model.addAttribute("userCreationForm", new RegistrationDto());
        return "usercreation";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String createBusinessUser(@ModelAttribute("userCreationForm")RegistrationDto registrationDto,
                                    BindingResult bindingResult){
        registrationValidator.validate(registrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "usercreation";
        }
        userFacade.saveNewUser(registrationDto);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/isuser", method = RequestMethod.GET)
    public String roleUserReturnPage() {
        if(userFacade.userHasAdminRole(getUserLogin())) {
            return "redirect:/admin";
        } else if (userFacade.userHasBusinessRole(getUserLogin())){
            return "redirect:/businessdata";
        } else {
            return "redirect:/catalog";
        }
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
