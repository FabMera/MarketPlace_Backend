package com.marketplace.backend.users.exceptions;

public class UserNotFoundException extends RuntimeException{

        public UserNotFoundException(String message) {
            super(message);
        }
}
