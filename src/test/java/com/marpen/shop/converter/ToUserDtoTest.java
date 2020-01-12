package com.marpen.shop.converter;

import com.marpen.shop.dto.UserDto;
import com.marpen.shop.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ToUserDtoTest {
    private static final String LOGIN = "natali";
    private static final String NAME = "Kate";
    private static final String EMPTY = "";
    private static final int ROLE_ID = 1;
    private static final String SURNAME = "Gorn";
    private static final String EMAIL = "gorn@mail.ru";
    private static final String PHONE = "3089078";
    private static final Date BIRTH_DATE = new Date();
    private static final boolean STATUS_ID = true;
    private static final String STATUS = "active";
    private static final String ADDRESS = "Минск, 98а-4";
    private User USER = new User(LOGIN, EMPTY, ROLE_ID, NAME, SURNAME, EMAIL, PHONE, BIRTH_DATE, ADDRESS, STATUS_ID);

    @InjectMocks
    private ToUserDto toUserDto;

    @Test
    public void testConvert() {
        UserDto actual = toUserDto.convert(USER);
        Assert.assertEquals(LOGIN, actual.getLogin());
        Assert.assertEquals(NAME, actual.getName());
        Assert.assertEquals(SURNAME, actual.getSurname());
        Assert.assertEquals(EMAIL, actual.getEmail());
        Assert.assertEquals(STATUS, actual.getStatus());
        Assert.assertEquals(PHONE, actual.getPhone());
        Assert.assertEquals(ADDRESS, actual.getAddress());
    }
}