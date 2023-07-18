package com.marketplace.backend.users.repositories;

import com.marketplace.backend.users.dtos.UserDto;
import com.marketplace.backend.users.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //Buscar por username
    User findByUsername(String username);
}
