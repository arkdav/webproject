package com.marpen.shop.service.impl;

import com.marpen.shop.model.User;
import com.marpen.shop.service.SecurityService;
import com.marpen.shop.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private UserDetailsService userDetailsService;

    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserService userService, UserDetailsService userDetailsService){
        this.authenticationManager=authenticationManager;
        this.userService = userService;
        this.userDetailsService=userDetailsService;
    }

    @Override
    public User findLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication instanceof AnonymousAuthenticationToken)
                        ? null : userService.getUserByLogin(authentication.getName());
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails!=null){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(authenticationToken);
            if (authenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }
}
