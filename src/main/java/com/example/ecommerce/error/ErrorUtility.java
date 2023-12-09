package com.example.ecommerce.error;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ErrorUtility {

    public static ResponseEntity<?> errorValidationResponseEntity(Errors errors, HttpStatus errorType) {
        List<ResponseError> responseErrors = new ArrayList<>();
        errors.getAllErrors().forEach(e -> {
            ResponseError responseError = new ResponseError();
            responseError.setField(((FieldError)e).getField());
            responseError.setMessage(e.getDefaultMessage());
            responseErrors.add(responseError);
        });

        return new ResponseEntity<>(responseErrors, errorType);
    }
}
