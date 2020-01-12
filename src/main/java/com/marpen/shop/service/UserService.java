package com.marpen.shop.service;

import com.marpen.shop.model.User;

import java.util.List;

public interface UserService {

    void saveCustomer(User user);

    void saveBusinessUser(User user);

    User getUserByLogin(String login);

    void update(User user);

    void delete(User user);

    void changeUserStatus(User user);

    List<User> getUserListByRoleAndStatus(String roleName, String status, int page, int perPage);

    String getUserRoleName(String username);

    int getAmountOfUsers(String role, String status);

}