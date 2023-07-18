package com.marketplace.backend.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoUpdate {
    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String password;
}
