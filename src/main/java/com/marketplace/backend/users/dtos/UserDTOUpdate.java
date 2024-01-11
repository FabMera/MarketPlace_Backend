package com.marketplace.backend.users.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOUpdate {

    private String nombre;
    private String apellido;
    private String username;
    @Min(value = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    private String image;
}
