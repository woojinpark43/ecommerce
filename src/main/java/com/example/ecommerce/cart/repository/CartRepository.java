package com.example.ecommerce.cart.repository;

import com.example.ecommerce.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByEmail(String email);
}
