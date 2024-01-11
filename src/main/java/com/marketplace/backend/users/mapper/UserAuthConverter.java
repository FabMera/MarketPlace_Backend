package com.marketplace.backend.users.mapper;


import com.marketplace.backend.users.dtos.UserDTOAuth;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthConverter {

    private final ModelMapper modelMapper;

    public UserDTOAuth convertToDto(UserMarketPlace userMarketPlace) {
        return modelMapper.map(userMarketPlace, UserDTOAuth.class);
    }

    public UserMarketPlace convertToEntity(UserDTOAuth userDTOAuth) {
        return modelMapper.map(userDTOAuth, UserMarketPlace.class);
    }


}
