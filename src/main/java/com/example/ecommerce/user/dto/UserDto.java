package com.example.ecommerce.user.dto;

import com.example.ecommerce.user.dto.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private  String  email;

    @NotBlank(message = "password is required")
    @Size(min = 3, message = "password should be at least 3 digit")
    private Integer password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
