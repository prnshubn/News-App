package com.wipro.newsapp.userservice.exception;

public class ReportAlreadyExistException extends RuntimeException {
    public ReportAlreadyExistException(String msg) {
        super(msg);
    }
}
