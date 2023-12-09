package com.example.ecommerce.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.ecommerce.error.ErrorUtility;
import com.example.ecommerce.jwt.JWTUtils;
import com.example.ecommerce.user.dto.UserDto;
import com.example.ecommerce.user.dto.UserLogin;
import com.example.ecommerce.user.exception.PasswordNotMatchException;
import com.example.ecommerce.user.exception.UserAlreadyExsistsException;
import com.example.ecommerce.user.exception.UserNotFoundException;
import com.example.ecommerce.user.service.UserService;
import com.example.ecommerce.user.dto.UserLoginToken;
import com.example.ecommerce.user.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto user, Errors errors) {

        if (errors.hasErrors()) {
            return ErrorUtility.errorValidationResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        boolean registerSuccess = userService.register(user);

        if(!registerSuccess) {
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
        if (errors.hasErrors()) {
            return ErrorUtility.errorValidationResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        UserDto user = userService.findUserByEmail(userLogin.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User is not found"));

        boolean passwordMatch = PasswordUtils.equalPassword(userLogin.getPassword(),user.getPassword());

        if(!passwordMatch) {
            throw new PasswordNotMatchException("password does not match");
        }

        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);

        String token = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getEmail())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("ecommerce".getBytes()));

        return ResponseEntity.ok(UserLoginToken.builder().token(token).build());
    }

    @PatchMapping("/user/login")
    public ResponseEntity<?> refreshToken(HttpServletRequest request){
        String token = request.getHeader("F-Token");
        String email = "";
        try{
            email = JWTUtils.getIssuer(token);
        } catch(SignatureVerificationException e){
            return new ResponseEntity<>("token information is not correct", HttpStatus.BAD_REQUEST);
        }

        UserDto user = userService.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User is not found"));

        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);

        String newToken = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getEmail())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("ecommerce".getBytes()));

        return ResponseEntity.ok(UserLoginToken.builder().token(newToken).build());

    }

    @DeleteMapping("/user/login")
    public ResponseEntity<?> removeToken(@RequestHeader("F-TOKEN") String token) {
        try{
            JWTUtils.getIssuer(token);
        } catch(SignatureVerificationException e){
            return new ResponseEntity<>("token information is not correct", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserAlreadyExsistsException.class)
    public ResponseEntity<?> handlerUserAlreadyExsistsException(UserAlreadyExsistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlerUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<?> handlerPasswordNotMatchException(PasswordNotMatchException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
