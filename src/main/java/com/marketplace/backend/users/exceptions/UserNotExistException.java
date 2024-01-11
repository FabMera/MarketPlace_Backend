package com.marketplace.backend.users.exceptions;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("El usuario no existe");
    }
}
