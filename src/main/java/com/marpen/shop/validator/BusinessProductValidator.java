package com.marpen.shop.validator;

import com.marpen.shop.dto.BusinessProductDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BusinessProductValidator implements Validator {

    public BusinessProductValidator() {
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BusinessProductDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BusinessProductDto businessProductDto = (BusinessProductDto) o;

        if (!businessProductDto.getName().isEmpty() && (businessProductDto.getName().length() < 4 || businessProductDto.getName().length() > 32)) {
            errors.rejectValue("name", "Size.business.name");
        }
        if(!businessProductDto.getPrice().isEmpty()) {
            try {
                Double.parseDouble(businessProductDto.getPrice());
            } catch (NumberFormatException e) {
                errors.rejectValue("price", "Format.business.price");
            }
        }
        if (!businessProductDto.getDescription().isEmpty() && (businessProductDto.getDescription().length() < 4 || businessProductDto.getDescription().length() > 500)) {
            errors.rejectValue("description", "Size.business.description");
        }
    }
}
