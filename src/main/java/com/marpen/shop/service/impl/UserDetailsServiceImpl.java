package com.marpen.shop.service.impl;

import com.marpen.shop.dao.RoleDao;
import com.marpen.shop.model.User;
import com.marpen.shop.service.UserService;
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
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService, RoleDao roleDao){
        this.userService=userService;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        Set<GrantedAuthority> roles= new HashSet<>();
        roles.add(new SimpleGrantedAuthority(roleDao.getRoleNameById(user.getRoleId())));
        UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        return userDetails;
    }
}
