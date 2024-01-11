package com.marketplace.backend.users.mapper;

import com.marketplace.backend.users.dtos.CreateUserDTO;
import com.marketplace.backend.users.dtos.UserDTO;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserConverter {

    private final ModelMapper modelMapper;

    public CreateUserDTO convertToDto(UserMarketPlace userMarketPlace) {
        return modelMapper.map(userMarketPlace, CreateUserDTO.class);
    }

    public UserMarketPlace convertToEntity(CreateUserDTO createUserDTO) {
        return modelMapper.map(createUserDTO, UserMarketPlace.class);
    }

}
