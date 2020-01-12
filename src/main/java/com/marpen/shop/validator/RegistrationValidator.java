package com.marpen.shop.validator;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.service.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator implements Validator {

    private UserService userService;

    public RegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationDto user = (RegistrationDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() < 6 || user.getLogin().length() > 16) {
            errors.rejectValue("login", "Size.login");
        }
        try{
            if(userService.getUserByLogin(user.getLogin()) != null){
                errors.rejectValue("login", "Duplicate.login");
            }
        } catch (ObjectNotFoundException ignored){ }

        Pattern patternForUsername = Pattern.compile("^[a-z0-9_-]{6,16}$");
        if (!patternForUsername.matcher(user.getLogin()).find()) {
            errors.rejectValue("login", "Format.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdate", "Required");
        try {
            Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(user.getBirthdate());
            if(date1.compareTo(new Date())>=0){
                errors.rejectValue("birthdate", "MoreThanNow.birthdate");
            }
        } catch (ParseException e) {
            errors.rejectValue("birthdate", "Format.birthdate");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (user.getName().length() < 2 || user.getName().length() > 32) {
            errors.rejectValue("name", "Size.name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Required");
        if (user.getSurname().length() < 2 || user.getSurname().length() > 32) {
            errors.rejectValue("surname", "Size.surname");
        }

        Pattern patternForName = Pattern.compile("^[A-Za-zА-Яа-я]{2,32}$");
        if (!patternForName.matcher(user.getName()).find()) {
            errors.rejectValue("name", "Format.name");
        }
        if (!patternForName.matcher(user.getSurname()).find()) {
            errors.rejectValue("surname", "Format.surname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Required");
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(user.getPhone());
        if (!matcher.find()) {
            errors.rejectValue("phone", "Format.phone");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Required");
        if(!user.getAddress().isEmpty() && (user.getAddress().length()<2 || user.getAddress().length()>32)) {
            errors.rejectValue("address", "Format.address");
        }
    }

}
