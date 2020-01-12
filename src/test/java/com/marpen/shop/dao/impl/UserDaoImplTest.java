package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/test-dao-persistance-context.xml"})
@Transactional
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    private static final String LOGIN1 = "natali";
    private static final String LOGIN2 = "kolya";
    private static final String LOGIN3 = "misha";
    private static final String PASSWORD = "$2a$11$j4xr0InYy/WVRT3AChOlWOQl6sjJXNRhAyW.irpVqMY4wkqZEQ0Cq";
    private static final int ROLE_ID_1 = 1;
    private static final int ROLE_ID_2 = 3;
    private static final int ROLE_ID_NO_ROLE = 2;
    private static final String NAME1 = "Kate";
    private static final String NAME2 = "May";
    private static final String NAME3 = "Due";
    private static final String SURNAME = "Gorn";
    private static final String EMAIL = "gorn@mail.ru";
    private static final String PHONE = "3089078";
    private static final Date BIRTH_DATE = new GregorianCalendar(2003, Calendar.SEPTEMBER, 25).getTime();
    private static final boolean STATUS = true;
    private static final String ADDRESS = "Minsk, 98а-4";
    private static final String NO_SUCH_LOGIN = "abrakadabra";
    private static final int PAGE_ID = 1;
    private static final int PER_PAGE = 10;
    private static final User user2 = new User(LOGIN2, PASSWORD, ROLE_ID_1, NAME2, SURNAME, EMAIL, PHONE, BIRTH_DATE, ADDRESS, STATUS);
    private static final User user3 = new User(LOGIN3, PASSWORD, ROLE_ID_2, NAME3, SURNAME, EMAIL, PHONE, BIRTH_DATE, ADDRESS, STATUS);
    private static final User user1 = new User(LOGIN1, PASSWORD, ROLE_ID_1, NAME1, SURNAME, EMAIL, PHONE, BIRTH_DATE, ADDRESS, STATUS);
    private List<User> usersWithRoles = new ArrayList<>();
    private List<User> allUsersWithRoleId2 = new ArrayList<>();

    @Test
    public void testGetByLoginWithExistingLogin() {
        User actual = userDao.get(LOGIN1);
        Assert.assertEquals(LOGIN1, actual.getLogin());
        Assert.assertEquals(PASSWORD, actual.getPassword());
        Assert.assertEquals(ROLE_ID_1, actual.getRoleId());
        Assert.assertEquals(NAME1, actual.getName());
        Assert.assertEquals(SURNAME, actual.getSurname());
        Assert.assertEquals(EMAIL, actual.getEmail());
        Assert.assertEquals(PHONE, actual.getPhone());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(formatter.format(BIRTH_DATE), formatter.format(actual.getBirthDate()));
        Assert.assertEquals(ADDRESS, actual.getAddress());
        Assert.assertEquals(STATUS, actual.getStatus());
    }

    @Test
    public void testGetByLoginWithNoSuchLogin() {
        boolean isNull = false;
        try {
            userDao.get(NO_SUCH_LOGIN);
        } catch (ObjectNotFoundException exception) {
            isNull = true;
        }
        Assert.assertTrue(isNull);
    }

    @Test
    public void getUserListByRole() {
        usersWithRoles.add(user2);
        usersWithRoles.add(user1);
        List<User> actual = userDao.getUserListByRole(ROLE_ID_1, PAGE_ID, PER_PAGE);
        Assert.assertEquals(usersWithRoles, actual);
    }

    @Test
    public void getAllUsersByRole() {
        allUsersWithRoleId2.add(user3);
        List<User> actual = userDao.getAllUsersByRole(ROLE_ID_2);
        Assert.assertEquals(allUsersWithRoleId2, actual);
    }

    @Test
    public void getUserListWithoutRole() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(user2);
        allUsers.add(user3);
        allUsers.add(user1);
        List<User> actual = userDao.getUserListWithoutRole(ROLE_ID_NO_ROLE, PAGE_ID, PER_PAGE);
        Assert.assertEquals(allUsers, actual);
    }

    @Test
    public void getUserListByStatusWithoutRole() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(user2);
        allUsers.add(user3);
        allUsers.add(user1);
        String STATUS_ID = STATUS ? "1" : "0";
        List<User> actual = userDao.getUserListByStatusWithoutRole(ROLE_ID_NO_ROLE, STATUS_ID, PAGE_ID, PER_PAGE);
        Assert.assertEquals(allUsers, actual);
    }

    @Test
    public void getUserListByRoleAndStatus() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(user2);
        allUsers.add(user1);
        String STATUS_ID = STATUS ? "1" : "0";
        List<User> actual = userDao.getUserListByRoleAndStatus(ROLE_ID_1, STATUS_ID, PAGE_ID, PER_PAGE);
        Assert.assertEquals(allUsers, actual);
    }

}