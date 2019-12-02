package com.marpen.shop.dao.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.model.Role;
import org.hibernate.Session;

import java.util.List;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private RoleDaoImpl() {
        super();
    }

    private Session currentSession() {
        return super.getSession();
    }


    @Override
    @SuppressWarnings("unchecked")
    public int getRoleIdByRole(String role) {
        String sql = "Select * from roles where rolename like :role";
        List<Role> roles = currentSession().createSQLQuery(sql).addEntity(Role.class).setParameter("role", role).list();
        return roles.isEmpty() ? 0 : roles.get(0).getRoleId();
    }

}
