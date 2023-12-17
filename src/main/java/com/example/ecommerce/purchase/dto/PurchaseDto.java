package com.example.ecommerce.purchase.dto;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PurchaseDto {
    private int purchaseId;
    private String userId;
    private int cartId;
    private int productId;
    private int carNum;
    private String address;
    private Validate validate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
