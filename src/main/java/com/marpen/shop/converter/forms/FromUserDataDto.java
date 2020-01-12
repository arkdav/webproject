package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.model.User;
import com.marpen.shop.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FromUserDataDto implements Converter<UserDataDto, User> {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    public FromUserDataDto(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public User convert(UserDataDto userDataDto) {
        User user = userService.getUserByLogin(userDataDto.getLogin());
        if (userDataDto.getName() != null && !userDataDto.getName().isEmpty()) {
            user.setName(userDataDto.getName());
        }
        if (userDataDto.getPassword() != null && !userDataDto.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(userDataDto.getPassword()));
        }
        if (userDataDto.getSurname() != null && !userDataDto.getSurname().isEmpty()) {
            user.setSurname(userDataDto.getSurname());
        }
        if (userDataDto.getEmail() != null && !userDataDto.getEmail().isEmpty()) {
            user.setEmail(userDataDto.getEmail());
        }
        if (userDataDto.getPhone() != null && !userDataDto.getPhone().isEmpty()) {
            user.setPhone(userDataDto.getPhone());
        }
        if (userDataDto.getBirthdate() != null && !userDataDto.getBirthdate().isEmpty()) {
            try {
                user.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(userDataDto.getBirthdate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (userDataDto.getAddress() != null && !userDataDto.getAddress().isEmpty()) {
            user.setAddress(userDataDto.getAddress());
        }
        return user;
    }
}
