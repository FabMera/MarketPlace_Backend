package com.marketplace.backend.users.exceptions;

public class EmailAlReadyExceptions extends RuntimeException{

    public EmailAlReadyExceptions(String message) {
        super(message);
    }
}
