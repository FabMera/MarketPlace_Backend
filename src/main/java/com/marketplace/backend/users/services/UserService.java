package com.marketplace.backend.users.services;

import com.marketplace.backend.users.dtos.UserDto;
import com.marketplace.backend.users.dtos.UserDtoUpdate;
import com.marketplace.backend.users.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAllDto();
    List<User> findAllUser();
    Optional<UserDto> findUserById(Long id);
    User saveUser(User user);
    void removeUserById(Long id);
    Optional<UserDtoUpdate> updateUser(Long id, UserDtoUpdate userDtoUpdate);
}
