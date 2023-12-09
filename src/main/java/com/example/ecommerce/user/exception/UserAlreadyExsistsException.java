package com.example.ecommerce.user.exception;

public class UserAlreadyExsistsException extends RuntimeException {
    public UserAlreadyExsistsException(String s) {
        super(s);
    }
}