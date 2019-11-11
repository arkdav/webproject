package com.marpen.shop.service;

import com.marpen.shop.model.User;

public interface SecurityService {

    User findLoggedInUser();

    void autoLogin(String username, String password);

}