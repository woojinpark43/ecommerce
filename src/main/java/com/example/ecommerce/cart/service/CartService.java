package com.example.ecommerce.cart.service;

import com.example.ecommerce.cart.dto.CartDto;
import com.example.ecommerce.cart.dto.CartRegister;

import java.util.List;
import java.util.Optional;

public interface CartService {
    boolean register(CartRegister user);

    List<CartDto> getCartByEmail(String email);

    Optional<CartDto> updateCountById(Integer id, Integer count);

    Optional<CartDto> deleteCartById(Integer id);
}
