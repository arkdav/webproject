package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private UserDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public List<User> getUserList(int roleId) {
        String sql = "Select * from users where role_id like :role_id";
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class).setParameter("role_id", roleId).list();
        return users;
    }
}
