package com.marketplace.backend.users;

import com.marketplace.backend.users.exceptions.EmailAlReadyExceptions;
import com.marketplace.backend.users.exceptions.PassIncorrectException;
import com.marketplace.backend.users.exceptions.UserNotExistException;
import com.marketplace.backend.users.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlReadyExceptions.class)
    public ResponseEntity<String> handleEmailAlReadyExceptions(EmailAlReadyExceptions e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotExistException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(PassIncorrectException.class)
    public ResponseEntity<String> handlePassIncorrectException(PassIncorrectException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> handleUserNotExistException(UserNotExistException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la autenticación");
    }
    */
}
