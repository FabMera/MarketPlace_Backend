package com.marketplace.backend.users.mapper;

import com.marketplace.backend.users.dtos.UserDTOUpdate;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter {

    private final ModelMapper modelMapper;

    public UserDTOUpdate convertToDto(UserMarketPlace userMarketPlace) {
        return modelMapper.map(userMarketPlace, UserDTOUpdate.class);
    }

    public UserMarketPlace convertToEntity(UserDTOUpdate userDTOUpdate) {
        return modelMapper.map(userDTOUpdate, UserMarketPlace.class);
    }


}
