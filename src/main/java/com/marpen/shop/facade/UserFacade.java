package com.marpen.shop.facade;

import com.marpen.shop.dto.PageDto;
import com.marpen.shop.dto.RegistrationDto;
import com.marpen.shop.dto.UserDataDto;
import com.marpen.shop.dto.UserDto;

import java.util.List;

public interface UserFacade {

    void saveNewUser(RegistrationDto registrationDto);

    void update(String userLogin, UserDataDto userDataDto);

    boolean deleteUsers(String [] usersLogins);

    boolean changeUsersStatus(String [] usersLogins);

    UserDto getUserInformation(String username);

    List<UserDto> getUserListByRoleAndStatus(String roleName, String status, int page, int perPage);

    List<PageDto> getUserPagesList (String role, String status, int perPage);

    boolean userHasAdminRole(String username);

    boolean userHasBusinessRole(String username);
}
