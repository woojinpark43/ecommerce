package com.example.ecommerce.user.service;

import com.example.ecommerce.user.dto.UserDto;
import com.example.ecommerce.user.dto.UserLogin;

import java.util.Optional;

public interface UserService {
    boolean register(UserDto user);
    Optional<UserDto> findUserByEmail(String email);
}