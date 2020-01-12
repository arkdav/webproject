package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.ToPageDto;
import com.marpen.shop.converter.ToUserDto;
import com.marpen.shop.converter.forms.FromRegistrationDto;
import com.marpen.shop.converter.forms.FromUserDataDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.model.User;
import com.marpen.shop.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private FromRegistrationDto fromRegistrationDto;
    private FromUserDataDto fromUserDataDto;
    private ToUserDto toUserDto;
    private ToPageDto toPageDto;

    public UserFacadeImpl(UserService userService, FromRegistrationDto fromRegistrationDto,
                          FromUserDataDto fromUserDataDto, ToUserDto toUserDto, ToPageDto toPageDto) {
        this.userService = userService;
        this.fromRegistrationDto = fromRegistrationDto;
        this.fromUserDataDto = fromUserDataDto;
        this.toUserDto = toUserDto;
        this.toPageDto=toPageDto;
    }

    @Override
    public void saveNewUser(RegistrationDto registrationDto) {
        User user = fromRegistrationDto.convert(registrationDto);
        if (registrationDto.getRole().equals("u")) {
            userService.saveCustomer(user);
        } else if (registrationDto.getRole().equals("b")) {
            userService.saveBusinessUser(user);
        }
    }

    @Override
    public void update(String userLogin, UserDataDto userDataDto) {
        userDataDto.setLogin(userLogin);
        User user = fromUserDataDto.convert(userDataDto);
        userService.update(user);
    }

    @Override
    public boolean deleteUsers(String [] usersLogins) {
        boolean isDeleted=true;
        for (String login : usersLogins) {
            User user = userService.getUserByLogin(login);
            if(user!=null){
                userService.delete(user);
            } else {
                isDeleted=false;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean changeUsersStatus(String [] usersLogins) {
        boolean isChanged=true;
        for (String login : usersLogins) {
            User user = userService.getUserByLogin(login);
            if(user!=null){
                userService.changeUserStatus(user);
            } else {
                isChanged=false;
            }
        }
        return isChanged;
    }

    @Override
    public UserDto getUserInformation(String username) {
        User user = userService.getUserByLogin(username);
        UserDto userDto = null;
        if (user != null) {
            userDto = toUserDto.convert(user);
        }
        return userDto;
    }

    @Override
    public List<UserDto> getUserListByRoleAndStatus(String roleName, String status, int pageId, int perPage) {
        if (!roleName.isEmpty()) {
            if (roleName.equals("customer")) {
                roleName = "ROLE_CUSTOMER";
            } else if (roleName.equals("business")) {
                roleName = "ROLE_BUSINESS_USER";
            }
        }
        if(!status.isEmpty()){
            if(status.equals("active")){
                status="1";
            } else if(status.equals("inactive")){
                status="0";
            }
        }
        List<User> users = userService.getUserListByRoleAndStatus(roleName, status, pageId, perPage);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user :
                users) {
            UserDto userDto = toUserDto.convert(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public List<PageDto> getUserPagesList(String roleName, String status, int perPage) {
        if(!roleName.isEmpty()){
            if (roleName.equals("customer")) {
                roleName = "ROLE_CUSTOMER";
            } else if (roleName.equals("business")) {
                roleName = "ROLE_BUSINESS_USER";
            }
        }
        if(!status.isEmpty()){
            if(status.equals("active")){
                status="1";
            } else if(status.equals("inactive")){
                status="0";
            }
        }
        int amountOfProducts = this.userService.getAmountOfUsers(roleName, status);
        return toPageDto.convert(amountOfProducts, perPage);
    }

    @Override
    public boolean userHasAdminRole(String username) {
        return userService.getUserRoleName(username).equals("ROLE_ADMIN");
    }

    @Override
    public boolean userHasBusinessRole(String username) {
        return userService.getUserRoleName(username).equals("ROLE_BUSINESS_USER");
    }

}
