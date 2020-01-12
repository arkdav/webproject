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
        user.setRoleId(roleDao.getRoleByRoleName("ROLE_CUSTOMER").getRoleId());
        user.setStatus(true);
        userDao.save(user);
    }

    @Override
    public void saveBusinessUser(User user) {
        user.setRoleId(roleDao.getRoleByRoleName("ROLE_BUSINESS_USER").getRoleId());
        user.setStatus(true);
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
    public void changeUserStatus(User user) {
        boolean status = user.getStatus();
        if(!status){
            user.setStatus(true);
        } else {
            user.setStatus(false);
        }
        userDao.update(user);
    }

    @Override
    public List<User> getUserListByRoleAndStatus(String roleName, String status, int page, int perPage){
        List<User> users;
        if(roleName.isEmpty()){
            int adminRoleId = roleDao.getRoleByRoleName("ROLE_ADMIN").getRoleId();
            if(status.isEmpty()){
               users= userDao.getUserListWithoutRole(adminRoleId, page, perPage);
            } else {
                users = userDao.getUserListByStatusWithoutRole(adminRoleId, status, page, perPage);
            }
        } else {
            int roleId=roleDao.getRoleByRoleName(roleName).getRoleId();
            if(status.isEmpty()){
                users = userDao.getUserListByRole(roleId, page, perPage);
            } else {
                users = userDao.getUserListByRoleAndStatus(roleId, status, page, perPage);
            }
        }
        return users;
    }

    @Override
    public String getUserRoleName(String username) {
        User user=userDao.get(username);
        return user!=null ? roleDao.get(user.getRoleId()).getRolename() : "";
    }

    @Override
    public int getAmountOfUsers(String role, String status) {
        List <User> users;
        if(role.isEmpty()) {
            int adminRoleId = roleDao.getRoleByRoleName("ROLE_ADMIN").getRoleId();
            if(status.isEmpty()){
                users = userDao.getAllUsersWithoutRole(adminRoleId);
            } else {
                users= userDao.getAllUsersByStatusWithoutRole(adminRoleId, status);
            }
        } else {
            int roleId = roleDao.getRoleByRoleName(role).getRoleId();
            if(status.isEmpty()){
                users = userDao.getAllUsersByRole(roleId);
            } else {
                users=userDao.getAllUsersByRoleAndStatus(roleId,status);
            }
        }
        return users!=null ? users.size() : 0;
    }
}
