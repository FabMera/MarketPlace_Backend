package com.marketplace.backend.users.repositories;

import com.marketplace.backend.users.model.entities.UserMarketPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserMarketPlace, UUID> {

    //Buscar por EMAIL
    Optional<UserMarketPlace> findByEmail(String email);
}
