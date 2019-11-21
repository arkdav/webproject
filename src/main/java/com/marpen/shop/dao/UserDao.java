package com.marpen.shop.dao;

import com.marpen.shop.model.User;

public interface UserDao extends GenericDao<User>{

    User getUserByUsername(String username);

    User getUserById(int userId);
}
