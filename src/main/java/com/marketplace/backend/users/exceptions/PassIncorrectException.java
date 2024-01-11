package com.marketplace.backend.users.exceptions;

public class PassIncorrectException extends RuntimeException{

    public PassIncorrectException() {
        super("Contraseña incorrecta");
    }
}
