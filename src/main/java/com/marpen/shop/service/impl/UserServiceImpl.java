package com.marpen.shop.service.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import com.marpen.shop.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void saveCustomer(User user) {
        user.setRoleId(roleDao.getRoleIdByRole("ROLE_CUSTOMER"));
        userDao.save(user);
    }

    @Override
    public void saveBusinessUser(User user) {
        user.setRoleId(roleDao.getRoleIdByRole("ROLE_BUSINESS_USER"));
        userDao.save(user);
    }

    @Override
    public User getUserByLogin(String username) {
        return userDao.get(username);
    }

    @Override
    public void update(User user){
        userDao.update(user);
    }

    @Override
    public void delete(User user){
        userDao.delete(user);
    }

    @Override
    public List<User> getUserListByRole(String rolename){
        int roleId=roleDao.getRoleIdByRole(rolename);
        return userDao.getUserList(roleId);
    }

}
