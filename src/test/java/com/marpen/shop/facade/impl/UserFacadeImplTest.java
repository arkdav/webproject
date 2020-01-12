package com.marpen.shop.facade.impl;

import com.marpen.shop.model.User;
import com.marpen.shop.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeImplTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserFacadeImpl userFacade;

    private static final String[] USER_LOGINS = {"Adam", "Eva"};
    private static final String LOGIN_FIRST = "Adam";
    private static final String LOGIN_SECOND = "Eva";
    private static final boolean isTrue = true;
    private static final boolean isFalse = false;
    private static final String EMPTY = "";
    private static final int ROLE_ID = 1;
    private static final Date DATE_NOW = new Date();
    private static final String USER_ROLE_NAME_ADMIN = "ROLE_ADMIN";
    private static final String USER_ROLE_NAME_BUSINESS = "ROLE_BUSINESS_USER";
    private static final User USER_FIRST = new User(LOGIN_FIRST, EMPTY, ROLE_ID, EMPTY, EMPTY, EMPTY, EMPTY, DATE_NOW, EMPTY, isTrue);
    private static final User USER_SECOND = new User("Adam", EMPTY, ROLE_ID, EMPTY, EMPTY, EMPTY, EMPTY, DATE_NOW, EMPTY, isTrue);
    private static final User USER_NULL = null;

    @Test
    public void changeUsersStatus_True() {
        Mockito.when(userService.getUserByLogin(LOGIN_FIRST)).thenReturn(USER_FIRST);
        Mockito.when(userService.getUserByLogin(LOGIN_SECOND)).thenReturn(USER_SECOND);
        boolean actual = userFacade.changeUsersStatus(USER_LOGINS);
        assertEquals(isTrue, actual);
    }

    @Test
    public void changeUsersStatus_False() {
        Mockito.when(userService.getUserByLogin(LOGIN_FIRST)).thenReturn(USER_NULL);
        Mockito.when(userService.getUserByLogin(LOGIN_SECOND)).thenReturn(USER_SECOND);
        boolean actual = userFacade.changeUsersStatus(USER_LOGINS);
        assertEquals(isFalse, actual);
    }

    @Test
    public void deleteUsers_True() {
        Mockito.when(userService.getUserByLogin(LOGIN_FIRST)).thenReturn(USER_FIRST);
        Mockito.when(userService.getUserByLogin(LOGIN_SECOND)).thenReturn(USER_SECOND);
        boolean actual = userFacade.deleteUsers(USER_LOGINS);
        assertEquals(isTrue, actual);
    }

    @Test
    public void deleteUsers_False() {
        Mockito.when(userService.getUserByLogin(LOGIN_FIRST)).thenReturn(USER_NULL);
        Mockito.when(userService.getUserByLogin(LOGIN_SECOND)).thenReturn(USER_SECOND);
        boolean actual = userFacade.deleteUsers(USER_LOGINS);
        assertEquals(isFalse, actual);
    }

    @Test
    public void userHasAdminRole_True() {
        Mockito.when(userService.getUserRoleName(LOGIN_FIRST)).thenReturn(USER_ROLE_NAME_ADMIN);
        boolean actual = userFacade.userHasAdminRole(LOGIN_FIRST);
        assertEquals(isTrue, actual);
    }

    @Test
    public void userHasAdminRole_False() {
        Mockito.when(userService.getUserRoleName(LOGIN_SECOND)).thenReturn(USER_ROLE_NAME_BUSINESS);
        boolean actual = userFacade.userHasAdminRole(LOGIN_SECOND);
        assertEquals(isFalse, actual);
    }

    @Test
    public void userHasBusinessRole_True() {
        Mockito.when(userService.getUserRoleName(LOGIN_FIRST)).thenReturn(USER_ROLE_NAME_BUSINESS);
        boolean actual = userFacade.userHasBusinessRole(LOGIN_FIRST);
        assertEquals(isTrue, actual);
    }

    @Test
    public void userHasBusinessRole_False() {
        Mockito.when(userService.getUserRoleName(LOGIN_SECOND)).thenReturn(USER_ROLE_NAME_ADMIN);
        boolean actual = userFacade.userHasBusinessRole(LOGIN_SECOND);
        assertEquals(isFalse, actual);
    }
}