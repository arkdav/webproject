package com.marpen.shop.dao;

import com.marpen.shop.model.Role;

public interface RoleDao extends GenericDao<Role>{

    int getRoleIdByRole(String role);
}
