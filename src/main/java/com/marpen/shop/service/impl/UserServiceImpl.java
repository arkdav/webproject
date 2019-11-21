package com.marpen.shop.service.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import com.marpen.shop.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void save(User user) {
        user.setRoleId(roleDao.getRoleIdByRole("ROLE_CUSTOMER"));
        userDao.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void update(User user){
        userDao.update(user);
    }

    @Override
    public void delete(User user){
        userDao.delete(user);
    }

}
