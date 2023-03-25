package com.wipro.newsapp.userservice.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String msg) {
        super(msg);
    }
}
