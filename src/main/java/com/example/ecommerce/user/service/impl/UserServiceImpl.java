package com.example.ecommerce.user.service.impl;

import com.example.ecommerce.product.dto.ProductDto;
import com.example.ecommerce.product.entity.Product;
import com.example.ecommerce.user.dto.UserDto;
import com.example.ecommerce.user.dto.UserLogin;
import com.example.ecommerce.user.entity.UserEntity;
import com.example.ecommerce.user.exception.UserAlreadyExsistsException;
import com.example.ecommerce.user.repository.UserRepository;
import com.example.ecommerce.user.service.UserService;
import com.example.ecommerce.user.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);

        if(!optionalUser.isPresent()) {
            return Optional.empty();
        }

        UserEntity userEntity = optionalUser.get();

        return convertToUserDto(userEntity);
    }

    @Override
    public boolean register(UserDto user) {

        Optional<UserEntity> userEntity = userRepository.findByEmail(user.getEmail());

        if(userEntity.isPresent()) {
            throw new UserAlreadyExsistsException("user email is already being used.");
        }

        String encodedPassword = PasswordUtils.passwordEncoder().encode(user.getPassword());
        System.out.println(encodedPassword);


        UserEntity newUser = UserEntity.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(encodedPassword)
                .role(user.getRole())
                .build();

        userRepository.save(newUser);

        return true;
    }

    public Optional<UserDto> convertToUserDto(UserEntity userEntity) {
        return Optional.ofNullable(UserDto.builder()
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build());
    }

    public Optional<UserLogin> convertToUserLogin(UserEntity userEntity) {
        return Optional.ofNullable(UserLogin.builder()
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .build());
    }
}
