package com.wipro.newsapp.editorvoice.exception;

public class EditorialAlreadyExistsException extends RuntimeException {

    private String message;

    public EditorialAlreadyExistsException(String message) {
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
        return "EditorialAlreadyExistsException [message=" + message + "]";
    }
}