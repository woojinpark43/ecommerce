package com.example.ecommerce.cart.exception;

public class InvalidCartId extends RuntimeException {
    public InvalidCartId(String s) {
        super(s);
    }
}
