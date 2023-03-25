package com.wipro.newsapp.editorvoice.exception;

public class EditorialNotFoundException extends RuntimeException {

    private String message;

    public EditorialNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EditorialNotFoundException [message=" + message + "]";
    }
}