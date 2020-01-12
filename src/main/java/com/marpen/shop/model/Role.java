package com.marpen.shop.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Proxy(lazy = false)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int roleId;

    @Column(name = "rolename")
    private String rolename;

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, rolename);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId &&
                Objects.equals(rolename, role.rolename);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
