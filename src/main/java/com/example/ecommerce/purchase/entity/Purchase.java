package com.example.ecommerce.purchase.entity;

import com.example.ecommerce.purchase.dto.Validate;
import com.example.ecommerce.user.dto.type.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Purchase")
@Builder
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    private String userId;
    private int cartId;
    private int productId;
    private int carNum;
    private String address;
    @Enumerated(EnumType.STRING)
    private Validate validate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
