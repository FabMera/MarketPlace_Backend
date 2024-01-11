package com.marketplace.backend.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRespDTO {

    private String jwtToken;

    public JwtRespDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
