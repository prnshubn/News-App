package com.wipro.newsapp.editorvoice.exception;

import com.wipro.newsapp.editorvoice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EditorialAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> editorialAlreadyExists(EditorialAlreadyExistsException editorialAlreadyExists) {
        ErrorResponse errorResponse = new ErrorResponse(editorialAlreadyExists.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EditorialNotFoundException.class)
    public ResponseEntity<ErrorResponse> editorialNotFound(EditorialNotFoundException editorialNotFound) {
        ErrorResponse errorResponse = new ErrorResponse(editorialNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
