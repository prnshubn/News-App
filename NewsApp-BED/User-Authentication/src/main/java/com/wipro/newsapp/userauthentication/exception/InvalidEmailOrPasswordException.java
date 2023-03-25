package com.wipro.newsapp.userauthentication.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InvalidEmailOrPasswordException extends RuntimeException {
    private final String message;

}
