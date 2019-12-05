package com.marpen.shop.facade;

import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.dto.UserDto;

import java.util.List;

public interface UserFacade {

    void saveCustomer(RegistrationDto registrationDto);

    void saveBusinessUser(RegistrationDto registrationDto);

    void update(String userLogin, UserDataDto userDataDto);

    void changeUserStatus(String userLogin);

    UserDto getUserInformation(String username);

    List<UserDto> getUserListByRole(String rolename);
}
