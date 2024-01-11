package com.marketplace.backend.users.services;

import com.marketplace.backend.users.auth.JWTAuthenticationProvider;
import com.marketplace.backend.users.dtos.JwtRespDTO;
import com.marketplace.backend.users.dtos.LoginDTO;
import com.marketplace.backend.users.exceptions.PassIncorrectException;
import com.marketplace.backend.users.exceptions.UserNotExistException;
import com.marketplace.backend.users.helpers.AuthHelpers;
import com.marketplace.backend.users.mapper.UserAuthConverter;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import com.marketplace.backend.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthHelpers {

    private final UserRepository userRepository;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthConverter userAuthConverter;


    @Override
    public JwtRespDTO login(LoginDTO loginDTO) {
        Optional<UserMarketPlace> userMP = userRepository.findByEmail(loginDTO.getEmail());
        UserMarketPlace userMarketPlace = userMP.orElseThrow(UserNotExistException::new);
        if (!passwordEncoder.matches(loginDTO.getPassword(), userMarketPlace.getPassword())) {
            throw new PassIncorrectException();
        }
        return new JwtRespDTO(jwtAuthenticationProvider.createToken(userAuthConverter.convertToDto(userMarketPlace)));
    }

    @Override
    public JwtRespDTO logout(String token) {
        String[] tokenSplit = token.split(" ");
        return new JwtRespDTO(jwtAuthenticationProvider.eliminarToken(tokenSplit[1]));
    }
}
