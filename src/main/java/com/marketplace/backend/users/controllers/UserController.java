package com.marketplace.backend.users.controllers;

import com.marketplace.backend.users.dtos.*;
import com.marketplace.backend.users.helpers.AuthHelpers;
import com.marketplace.backend.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

    private final UserService userService;
    public final AuthHelpers authHelpers;

    public UserController(UserService userService,AuthHelpers authHelpers) {
        this.userService = userService;
        this.authHelpers = authHelpers;

    }

    @GetMapping
    public List<CreateUserDTO> listarDTO() {
        return userService.findAllUsers();//200
    }


    @PostMapping("/register")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid UserDTO userDTO,BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        CreateUserDTO createUserDTO = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserDTO);
    }

    @PutMapping("/miperfil/{id}/edit")
    public ResponseEntity<?> editar(@RequestBody @Valid UserDTOUpdate userDtoUpdate, BindingResult result, @PathVariable UUID id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        CreateUserDTO userUpdated = userService.updateUser(id, userDtoUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @PostMapping("/miperfil/{id}/delete")
    public ResponseEntity<?> eliminar(@PathVariable UUID id) {
        userService.removeUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO,BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        JwtRespDTO response = authHelpers.login(loginDTO);
        return ResponseEntity.ok(response);
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
