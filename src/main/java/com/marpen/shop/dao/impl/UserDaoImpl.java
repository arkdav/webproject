package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<User> getUserListWithoutRole(int roleId, int page, int perPage) {
        String sql = "Select * from users where role_id <> "+roleId+" order by login limit " + (page - 1) + "," + perPage;
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class).list();
        return users;
    }

    @Override
    public List<User> getUserListByRole(int roleId, int page, int perPage) {
        String sql = "Select * from users where role_id like :role_id order by login"+" limit " + (page - 1) + "," + perPage;
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class)
                .setParameter("role_id", roleId).list();
        return users;
    }

    @Override
    public List<User> getUserListByStatusWithoutRole(int roleId, String status, int page, int perPage) {
        String sql = "Select * from users where status like :status and role_id <> "+roleId+" order by login"
                +" limit " + (page - 1) + "," + perPage;
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class)
                .setParameter("status", status).list();
        return users;
    }

    @Override
    public List<User> getUserListByRoleAndStatus(int roleId, String status, int page, int perPage) {
        String sql = "Select * from users where role_id like :role_id and status like :status order by login"
                +" limit " + (page - 1) + "," + perPage;
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class)
                .setParameter("role_id", roleId).setParameter("status", status).list();
        return users;
    }

    @Override
    public List<User> getAllUsersWithoutRole(int roleId){
        String sql = "Select * from users where role_id <> "+roleId;
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class).list();
        return users;
    }

    @Override
    public List<User> getAllUsersByRole(int roleId){
        String sql = "Select * from users where role_id like :role_id";
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class)
                .setParameter("role_id", roleId).list();
        return users;
    }

    @Override
    public List<User> getAllUsersByStatusWithoutRole(int roleId, String status) {
        String sql = "Select * from users where status like :status and role_id <> "+roleId;
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class)
                .setParameter("status", status).list();
        return users;
    }

    @Override
    public List<User> getAllUsersByRoleAndStatus(int roleId, String status) {
        String sql = "Select * from users where role_id like :role_id and status like :status";
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class)
                .setParameter("role_id", roleId).setParameter("status", status).list();
        return users;
    }



}
