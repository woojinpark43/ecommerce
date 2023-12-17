package com.example.ecommerce.user.entity;

import com.example.ecommerce.user.dto.type.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Builder
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;
}
