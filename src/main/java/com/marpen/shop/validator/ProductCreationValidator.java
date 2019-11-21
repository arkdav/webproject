package com.marpen.shop.validator;

import com.marpen.shop.dto.BusinessProductCreationDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductCreationValidator implements Validator {

    public ProductCreationValidator() {
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BusinessProductCreationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BusinessProductCreationDto product = (BusinessProductCreationDto) o;
        if (product.getName().length() < 4 || product.getName().length() > 32) {
            errors.rejectValue("name", "Size.business.name");
        }
        try {
            Double.parseDouble(product.getPrice());
        } catch (NumberFormatException e) {
            errors.rejectValue("price", "Format.business.price");
        }
        if (product.getDescription().length() < 6 || product.getDescription().length() > 200) {
            errors.rejectValue("description", "Size.business.description");
        }
    }
}
