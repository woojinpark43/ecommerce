package com.example.ecommerce.user.exception;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(String s) {
        super(s);
    }
}
