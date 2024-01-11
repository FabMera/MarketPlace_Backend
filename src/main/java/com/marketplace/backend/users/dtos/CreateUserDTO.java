package com.marketplace.backend.users.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String nombre;
    private String username;
    private String email;

}
