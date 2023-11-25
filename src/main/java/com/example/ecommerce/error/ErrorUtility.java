package com.example.ecommerce.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorUtility {

    public ResponseEntity<?> errorValidationResponseEntity(Errors errors) {
        List<ResponseError> responseErrors = new ArrayList<>();
        errors.getAllErrors().forEach(e -> {
            ResponseError responseError = new ResponseError();
            responseError.setField(((FieldError)e).getField());
            responseError.setMessage(e.getDefaultMessage());
            responseErrors.add(responseError);
        });

        return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
    }
}
