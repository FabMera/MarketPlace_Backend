package com.marketplace.backend.users.services;

import com.marketplace.backend.users.auth.Roles;
import com.marketplace.backend.users.dtos.CreateUserDTO;
import com.marketplace.backend.users.dtos.UserDTO;
import com.marketplace.backend.users.dtos.UserDTOUpdate;
import com.marketplace.backend.users.exceptions.EmailAlReadyExceptions;
import com.marketplace.backend.users.exceptions.UserNotFoundException;
import com.marketplace.backend.users.mapper.CreateUserConverter;
import com.marketplace.backend.users.mapper.UserConverter;
import com.marketplace.backend.users.model.entities.UserMarketPlace;
import com.marketplace.backend.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CreateUserConverter createUserConverter;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<CreateUserDTO> findAllUsers() {
        List<UserMarketPlace> userMarketPlaces = userRepository.findAll();
        return userMarketPlaces.stream().map(createUserConverter::convertToDto).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CreateUserDTO> findUserById(UUID id) {
        Optional<UserMarketPlace> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserMarketPlace userMarketPlace = userOptional.orElseThrow();
            return Optional.ofNullable(createUserConverter.convertToDto(userMarketPlace));
        }
        throw new NoSuchElementException("No existe el usuario con id: " + id);
    }

    @Override
    @Transactional
    public CreateUserDTO saveUser(UserDTO userDTO) {
        validateEmail(userDTO.getEmail());
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userDTO.setRole(Roles.USUARIO_PRINCIPAL);
        UserMarketPlace userMarketPlace = userConverter.convertToEntity(userDTO);
        userRepository.save(userMarketPlace);
        return createUserConverter.convertToDto(userMarketPlace);

    }

    @Override
    @Transactional
    public void removeUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CreateUserDTO updateUser(UUID id, UserDTOUpdate userDtoUpdate) {
        UserMarketPlace userMP = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        updateUserFields(userMP, userDtoUpdate);
        UserMarketPlace userUpdate = userRepository.save(userMP);
        return createUserConverter.convertToDto(userUpdate);
    }

    private void updateUserFields(UserMarketPlace userMP, UserDTOUpdate userDtoUpdate) {
        //Actualizamos solo los campos que no sean nulos.
        if (userDtoUpdate.getNombre() != null) {
            userMP.setNombre(userDtoUpdate.getNombre());
        }
        if (userDtoUpdate.getApellido() != null) {
            userMP.setApellido(userDtoUpdate.getApellido());
        }
        if (userDtoUpdate.getUsername() != null) {
            userMP.setUsername(userDtoUpdate.getUsername());
        }
        if (userDtoUpdate.getPassword() != null) {
            userMP.setPassword(userDtoUpdate.getPassword());
        }
        if (userDtoUpdate.getImage() != null) {
            userMP.setEmail(userDtoUpdate.getImage());
        }
    }

    private void validateEmail(String email) {
        Optional<UserMarketPlace> userMP = userRepository.findByEmail(email);
        if (userMP.isPresent()) {
            throw new EmailAlReadyExceptions("El email ya se encuentra registrado");
        }
    }
}
