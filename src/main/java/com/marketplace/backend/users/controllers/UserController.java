package com.marketplace.backend.users.controllers;

import com.marketplace.backend.users.dtos.UserDto;
import com.marketplace.backend.users.dtos.UserDtoUpdate;
import com.marketplace.backend.users.model.entities.User;
import com.marketplace.backend.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> listarDTO() {
        return userService.findAllDto();
    }

    @GetMapping("/allusers")
    public List<User> listar() {
        return userService.findAllUser();
    }

    @PostMapping
    public User crear(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/miperfil/{id}/edit")
    public ResponseEntity<?> editar(@RequestBody @Valid UserDtoUpdate userDtoUpdate, BindingResult result, @PathVariable Long id) {
        Optional<UserDtoUpdate> userOP = userService.updateUser(id, userDtoUpdate);
        if (result.hasErrors()) {
            return validation(result);
        }
        if (userOP.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOP.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
