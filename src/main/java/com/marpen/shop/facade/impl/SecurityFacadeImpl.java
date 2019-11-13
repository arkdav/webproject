package com.marpen.shop.facade.impl;

import com.marpen.shop.facade.SecurityFacade;
import com.marpen.shop.service.SecurityService;

public class SecurityFacadeImpl implements SecurityFacade {

    private SecurityService securityService;

    public SecurityFacadeImpl(SecurityService securityService){
        this.securityService=securityService;
    }

    @Override
    public void autologin(String login, String password){
        securityService.autoLogin(login, password);
    }

}
