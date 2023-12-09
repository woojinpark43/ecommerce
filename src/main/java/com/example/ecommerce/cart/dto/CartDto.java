package com.example.ecommerce.cart.dto;

import com.example.ecommerce.product.entity.Product;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDto {
    private int cartId;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "Product data is required")
    private Product product;
    @NotNull(message = "cart count")
    private int cartCount;
    @NotNull(message = "ordered date is needed")
    private LocalDateTime orderedAt;
    @NotNull(message = "updated date is needed")
    private LocalDateTime updateAt;
}
