package com.marpen.shop.facade;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.dto.UserDto;

public interface UserFacade {

    void save(RegistrationDto registrationDto);

    void update(String userLogin, UserDataDto userDataDto);

    UserDto getUserInformation(String username);

}
