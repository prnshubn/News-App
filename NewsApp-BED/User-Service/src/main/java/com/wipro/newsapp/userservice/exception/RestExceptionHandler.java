package com.wipro.newsapp.userservice.exception;

import com.wipro.newsapp.userservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RestExceptionHandler {
    @ExceptionHandler(ReportAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> ReportAlreadyExistException(
            ReportAlreadyExistException reportAlreadyExistException) {
        ErrorResponse errorResponse = new ErrorResponse(reportAlreadyExistException.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LikeStatusErrorException.class)
    public ResponseEntity<ErrorResponse> LikeStatusErrorException(
            ReportAlreadyExistException likeStatusErrorException) {
        ErrorResponse errorResponse = new ErrorResponse(likeStatusErrorException.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ErrorResponse> ReportNotFoundException(
            ReportAlreadyExistException reportNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(reportNotFoundException.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
