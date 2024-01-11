package com.marketplace.backend.users.mapper;

import com.marketplace.backend.users.dtos.UserDTO;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;


    public UserDTO convertToDto(UserMarketPlace userMarketPlace) {
        return modelMapper.map(userMarketPlace, UserDTO.class);
    }

    public UserMarketPlace convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserMarketPlace.class);
    }
}
