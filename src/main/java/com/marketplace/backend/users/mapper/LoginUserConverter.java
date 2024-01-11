package com.marketplace.backend.users.mapper;

import com.marketplace.backend.users.dtos.LoginDTO;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserConverter {

    private final ModelMapper modelMapper;

    public LoginDTO convertToLoginDto(UserMarketPlace userMarketPlace) {
        return modelMapper.map(userMarketPlace, LoginDTO.class);
    }

    public UserMarketPlace convertToEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, UserMarketPlace.class);
    }

}
