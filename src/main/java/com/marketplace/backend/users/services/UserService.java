package com.marketplace.backend.users.services;

import com.marketplace.backend.users.dtos.CreateUserDTO;
import com.marketplace.backend.users.dtos.UserDTO;
import com.marketplace.backend.users.dtos.UserDTOUpdate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<CreateUserDTO> findAllUsers();
    Optional<CreateUserDTO> findUserById(UUID id);
    CreateUserDTO saveUser(UserDTO userDTO);
    void removeUserById(UUID id);
    CreateUserDTO updateUser(UUID id, UserDTOUpdate userDtoUpdate);
}
