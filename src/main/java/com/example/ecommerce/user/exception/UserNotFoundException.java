package com.example.ecommerce.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String user_is_not_found) {
        super(user_is_not_found);
    }
}
