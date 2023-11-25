//package com.example.ecommerce.user.controller;
//
//import com.example.ecommerce.error.ErrorUtility;
//import com.example.ecommerce.user.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class UserController {
//
//    private final ErrorUtility errorUtility;
//
//    @PostMapping("/user/register")
//    public ResponseEntity<?> register(@RequestBody UserDto user, Errors errors) {
//        if (errors.hasErrors()) {
//            return errorUtility.errorValidationResponseEntity(errors);
//        }
//
//
//    }
//}
