package com.marpen.shop.dao;

import com.marpen.shop.model.User;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);

    int getRoleIdByUsername(String username);

    void save(User user);

    List<User> getUserList(int roleId);
}
