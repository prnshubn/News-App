package com.wipro.newsapp.adminservice.exception;

import com.wipro.newsapp.adminservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserAlreadyActiveException.class)
    public ResponseEntity<ErrorResponse> userAlreadyActive(UserAlreadyActiveException userAlreadyActive) {
        ErrorResponse errorResponse = new ErrorResponse(userAlreadyActive.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyInactiveException.class)
    public ResponseEntity<ErrorResponse> userAlreadyInactive(UserAlreadyInactiveException userAlreadyInactive) {
        ErrorResponse errorResponse = new ErrorResponse(userAlreadyInactive.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
