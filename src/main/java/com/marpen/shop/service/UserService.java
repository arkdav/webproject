package com.marpen.shop.service;

import com.marpen.shop.model.User;

public interface UserService {

    void save(User user);

    User getUserByLogin(String login);

    void update(User user);

    void delete(User user);
}