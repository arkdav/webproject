package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private Session session;

    public UserDaoImpl(SessionFactory sessionFactory) {
        if (super.getSessionFactory() == null) {
            super.setSessionFactory(sessionFactory);

        }
        this.session = super.getSession();
    }

    private Session currentSession() {
        return this.session;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByUsername(String username) {
        String sql = "select * from users where username like :username";
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class).setParameter("username", username).list();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserById(int userId) {
        String sql = "select * from users where user_id like :user_id";
        List<User> users = currentSession().createSQLQuery(sql).addEntity(User.class).setParameter("user_id", userId).list();
        return users.isEmpty() ? null : users.get(0);
    }
}
