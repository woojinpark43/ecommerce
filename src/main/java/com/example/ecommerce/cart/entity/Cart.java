package com.example.ecommerce.cart.entity;

import com.example.ecommerce.product.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cart")
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private String email;
    @ManyToOne
    private Product product;
    private int cartCount;
    private LocalDateTime orderedAt;
    private LocalDateTime updateAt;
}
