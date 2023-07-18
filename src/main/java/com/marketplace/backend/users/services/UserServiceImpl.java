package com.marketplace.backend.users.services;

import com.marketplace.backend.users.dtos.UserDto;
import com.marketplace.backend.users.dtos.UserDtoUpdate;
import com.marketplace.backend.users.model.entities.User;
import com.marketplace.backend.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAllDto() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.orElseThrow();
            return Optional.ofNullable(modelMapper.map(user, UserDto.class));
        }
        throw new NoSuchElementException("No existe el usuario con id: " + id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<UserDtoUpdate> updateUser(Long id, UserDtoUpdate userDtoUpdate) {
        Optional<User> userOptional = userRepository.findById(id);
        User userOp = null;
        if (userOptional.isPresent()) {
            User userDB = userOptional.orElseThrow();
            userDB.setEmail(userDtoUpdate.getEmail());
            userDB.setNombre(userDtoUpdate.getNombre());
            userDB.setApellido(userDtoUpdate.getApellido());
            userDB.setPassword(userDtoUpdate.getPassword());
            userOp = (userRepository.save(userDB));
            return Optional.ofNullable(modelMapper.map(userOp, UserDtoUpdate.class));
        }
        return Optional.empty();
    }
}
