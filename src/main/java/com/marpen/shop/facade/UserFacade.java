package com.marpen.shop.facade;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDto;

public interface UserFacade {

    void save(RegistrationDto registrationDto);

    UserDto getUserInformation(String username);
}
