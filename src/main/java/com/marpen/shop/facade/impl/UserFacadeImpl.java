package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.ToUserDto;
import com.marpen.shop.converter.forms.FromRegistrationDto;
import com.marpen.shop.converter.forms.FromUserDataDto;
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

    public UserFacadeImpl(UserService userService, FromRegistrationDto fromRegistrationDto,
                          FromUserDataDto fromUserDataDto, ToUserDto toUserDto) {
        this.userService = userService;
        this.fromRegistrationDto = fromRegistrationDto;
        this.fromUserDataDto = fromUserDataDto;
        this.toUserDto = toUserDto;
    }

    @Override
    public void saveCustomer(RegistrationDto registrationDto) {
        User user = fromRegistrationDto.convert(registrationDto);
        userService.saveCustomer(user);
    }

    @Override
    public void saveBusinessUser(RegistrationDto registrationDto) {
        User user = fromRegistrationDto.convert(registrationDto);
        userService.saveBusinessUser(user);
    }

    @Override
    public void update(String userLogin, UserDataDto userDataDto) {
        userDataDto.setLogin(userLogin);
        User user = fromUserDataDto.convert(userDataDto);
        userService.update(user);
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
    public List<UserDto> getUserListByRole(String rolename){
        List<User> users =userService.getUserListByRole(rolename);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user:
             users) {
           UserDto userDto=toUserDto.convert(user);
           userDtos.add(userDto);
        }
        return userDtos;
    }

}
