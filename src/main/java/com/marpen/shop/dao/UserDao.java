package com.marpen.shop.dao;

import com.marpen.shop.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{

    List<User> getUserListWithoutRole(int roleId, int page, int perPage);

    List<User> getUserListByRole(int roleId, int page, int perPage);

    List<User> getUserListByStatusWithoutRole(int roleId, String status, int page, int perPage);

    List<User> getUserListByRoleAndStatus(int roleId, String status, int page, int perPage);

    List<User> getAllUsersWithoutRole(int roleId);

    List<User> getAllUsersByRole(int roleId);

    List<User> getAllUsersByStatusWithoutRole(int roleId, String status);

    List<User> getAllUsersByRoleAndStatus(int roleId, String status);

}
