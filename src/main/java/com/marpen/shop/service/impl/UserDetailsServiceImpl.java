package com.marpen.shop.service.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.dao.UserDao;
import com.marpen.shop.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private RoleDao roleDao;
    private UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
            if (userDao.get(username).getStatus()) {
                User user = userDao.get(username);
                Set<GrantedAuthority> roles = new HashSet<>();
                roles.add(new SimpleGrantedAuthority(roleDao.get(user.getRoleId()).getRolename()));
                boolean isEnabled=true;
                if(!user.getStatus()) {
                    isEnabled=false;
                }
                userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), isEnabled,true, true, true, roles);
            }
        return userDetails;
    }
}
