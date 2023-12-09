package com.example.ecommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private  String  email;

    private String userName;

    @NotBlank(message = "password is required")
    @Size(min = 3, message = "password length should be at least 3")
    private String password;
}
