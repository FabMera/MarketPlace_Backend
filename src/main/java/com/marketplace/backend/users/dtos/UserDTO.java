package com.marketplace.backend.users.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String apellido;
    @NotNull
    @NotBlank
    private String username;
    @Email
    private String email;
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    private String image;
    private String role;
}
