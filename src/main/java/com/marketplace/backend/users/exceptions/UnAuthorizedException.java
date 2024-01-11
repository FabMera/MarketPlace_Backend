package com.marketplace.backend.users.exceptions;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super("No Autorizado,no tiene los permisos para realizar esta accion");
    }
}
