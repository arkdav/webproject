package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDto;
import com.marpen.shop.facade.UserFacade;
import com.marpen.shop.model.User;
import com.marpen.shop.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserFacadeImpl(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService=userService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public UserDto save(RegistrationDto userForm){

        User user= null;
        try {
            user = fromRegistrationDtoToUser(userForm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userService.save(user);
        return fromUserToUserDto(user);
    }

    public UserDto getUserInformation(String username){
        User user=userService.getUserByUsername(username);
        return fromUserToUserDto(user);
    }

    public UserDto fromUserToUserDto(User user){
        UserDto userDto=new UserDto(
                user.getUser_id(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthdate()
        );
        return userDto;
    }
    private User fromRegistrationDtoToUser(RegistrationDto registrationDto) throws ParseException {

        User user=new User();
        user.setUsername(registrationDto.getLogin());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getConfirmPassword()));
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setEmail(registrationDto.getEmail());
        user.setPhone(registrationDto.getPhone());
        user.setBirthdate(new SimpleDateFormat("dd.MM.yyyy").parse(registrationDto.getBirthdate()));
        return user;
    }
}
