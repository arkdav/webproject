package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private Session currentSession() {
        return super.getSession();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        String sql = "Select * from roles where rolename like :role";
        List<Role> roles = currentSession().createSQLQuery(sql).addEntity(Role.class).setParameter("role", roleName).list();
        return roles!=null ? roles.get(0) : null;
    }

}
