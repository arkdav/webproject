//package com.marpen.shop.dao.impl;
//
//import com.marpen.shop.dao.UserDao;
//import com.marpen.shop.model.Product;
//import com.marpen.shop.model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import java.util.List;
//
//public class UserDaoImpl implements UserDao {
//
//    private SessionFactory sessionFactory;
//
//    public UserDaoImpl(SessionFactory sessionFactory) {
//
//        this.sessionFactory=sessionFactory;
//    }
//
//    private Session currentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public User getUserByUsername(String username) {
//        String sql="select * from users where username like :username";
//        List<User> users=currentSession().createSQLQuery(sql).addEntity(User.class).setParameter("username", username).list();
//        return  users.get(0);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public int getRoleIdByUsername(String username){
//        String sql="Select role_id from users where username like :username";
//        List<User> users=currentSession().createSQLQuery(sql).addEntity(User.class).setParameter("username", username).list();
//        return  users.get(0).getRole_id();
//    }
//
//    @Override
//    public void save(User user){
//        currentSession().save(user);
//    }
//
//}
