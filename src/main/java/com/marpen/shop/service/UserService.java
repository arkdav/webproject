package com.marpen.shop.service;

import com.marpen.shop.model.User;

import java.util.List;

public interface UserService {

    void saveCustomer(User user);

    void saveBusinessUser(User user);

    User getUserByLogin(String login);

    void update(User user);

    void delete(User user);

    List<User> getUserListByRole(String rolename);

}