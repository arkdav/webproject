package com.marpen.shop.service;

import com.marpen.shop.model.User;

public interface UserService {

    void save(User user);

    User getUserByUsername(String username);
}