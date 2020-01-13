package com.marpen.shop.validator;

import com.marpen.shop.dto.UserDataDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator implements Validator {

    public UserDataValidator() {
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDataDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDataDto user = (UserDataDto) o;
        if (!user.getPassword().isEmpty() && (user.getPassword().length() < 6 || user.getPassword().length() > 32 )) {
            errors.rejectValue("password", "Size.password");
        }
        if((!user.getPassword().isEmpty() && !user.getPassword().equals(user.getConfirmPassword()))){
            errors.rejectValue("confirmPassword", "Different.password");
        }
        if(!user.getBirthdate().isEmpty()) {
            try {
                Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(user.getBirthdate());
                if(date1.compareTo(new Date())>=0){
                    errors.rejectValue("birthdate", "MoreThanNow.birthdate");
                }
            } catch (ParseException e) {
                errors.rejectValue("birthdate", "Format.birthdate");
            }
        }
        Pattern patternForName = Pattern.compile("^[A-Za-zА-Яа-я]{2,32}$");
        if (!user.getName().isEmpty() && (user.getName().length() < 2 || user.getName().length() > 32)) {
            errors.rejectValue("name", "Size.name");
            if (!patternForName.matcher(user.getName()).find()) {
                errors.rejectValue("name", "Format.name");
            }
        }
        if (!user.getSurname().isEmpty() &&(user.getSurname().length() < 2 || user.getSurname().length() > 32)) {
            errors.rejectValue("surname", "Size.surname");
            if (!patternForName.matcher(user.getSurname()).find()) {
                errors.rejectValue("surname", "Format.surname");
            }
        }
        if(!user.getPhone().isEmpty()) {
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(user.getPhone());
            if (!matcher.find()) {
                errors.rejectValue("phone", "Format.phone");
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Required");
        if(!user.getAddress().isEmpty() && (user.getAddress().length()<4 || user.getAddress().length()>32)) {
                errors.rejectValue("address", "Format.address");
        }
    }
}
