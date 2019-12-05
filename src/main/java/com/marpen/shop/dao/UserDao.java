package com.marpen.shop.dao;

import com.marpen.shop.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{

    List<User> getUserList(int roleId);
}
