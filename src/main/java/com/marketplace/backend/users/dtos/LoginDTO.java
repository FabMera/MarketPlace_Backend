package com.marketplace.backend.users.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank (message = "El Email es requerido")
    @Email (message = "El email debe ser válido")
    private String email;

    @NotBlank (message = "El password es requerido")
    @NotNull (message = "Password is required")
    private String password;
}
