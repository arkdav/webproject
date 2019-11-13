package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory=sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    @SuppressWarnings("unchecked")
    public int getRoleIdByRole(String role) {
        String sql="Select * from roles where rolename like :role";
        List <Role> roles=currentSession().createSQLQuery(sql).addEntity(Role.class).setParameter("role", role).list();
        return  roles.isEmpty()?null:roles.get(0).getRoleId();
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getRoleNameById(int roleId){
        String sql="Select * from roles where role_id like :role_id";
        List <Role> roles=currentSession().createSQLQuery(sql).addEntity(Role.class).setParameter("role_id", roleId).list();
        return  roles.isEmpty()?null:roles.get(0).getRolename();
    }
}
