package com.marketplace.backend.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTOAuth {

    //Variables para guardar los datos del usuario en el token
    private String username;
    private String email;
    private String role;
}
