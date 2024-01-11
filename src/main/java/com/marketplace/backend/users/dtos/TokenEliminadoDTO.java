package com.marketplace.backend.users.dtos;

public class TokenEliminadoDTO {
    private String mensaje;
    public TokenEliminadoDTO(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getMensaje() {
        return mensaje;
    }
}
