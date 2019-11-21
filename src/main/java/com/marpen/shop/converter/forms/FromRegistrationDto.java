package com.marpen.shop.converter.forms;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FromRegistrationDto implements Converter<RegistrationDto, User> {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public FromRegistrationDto(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User convert(RegistrationDto registrationDto) {
        User user = new User();
        try {
            user.setUsername(registrationDto.getLogin());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getConfirmPassword()));
            user.setName(registrationDto.getName());
            user.setSurname(registrationDto.getSurname());
            user.setEmail(registrationDto.getEmail());
            user.setPhone(registrationDto.getPhone());
            user.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(registrationDto.getBirthdate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }
}
