package com.marpen.shop.converter;

import com.marpen.shop.dto.UserDto;
import com.marpen.shop.model.User;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;

public class ToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setBirthdate(new SimpleDateFormat("dd.MM.yyyy").format(user.getBirthDate()));
        userDto.setAddress(user.getAddress());
        String status;
        if(user.getStatus()){
            status="active";
        } else {
            status="inactive";
        }
        userDto.setStatus(status);
        return userDto;
    }
}