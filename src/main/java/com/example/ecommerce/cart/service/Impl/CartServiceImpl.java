package com.example.ecommerce.cart.service.Impl;

import com.example.ecommerce.cart.dto.CartDto;
import com.example.ecommerce.cart.entity.Cart;
import com.example.ecommerce.cart.dto.CartRegister;
import com.example.ecommerce.cart.repository.CartRepository;
import com.example.ecommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public boolean register(CartRegister cart) {
        Cart newCart = Cart.builder()
                .cartId(cart.getCartId())
                .product(cart.getProduct())
                .cartCount(cart.getCartCount())
                .email(cart.getEmail())
                .orderedAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        cartRepository.save(newCart);

        return true;
    }

    @Override
    public List<CartDto> getCartByEmail(String email) {
        List<Cart> cartList =  cartRepository.findAllByEmail(email);
        List<CartDto> cartListDto = new ArrayList<>();
        cartList.forEach(e -> {
            cartListDto.add(convertToCartDto(e));
        });

        return cartListDto;
    }

    @Override
    public Optional<CartDto> updateCountById(Integer id, Integer count) {
        Optional<Cart> cart =  cartRepository.findById(id);
        if(!cart.isPresent()) {
            return Optional.empty();
        }
        Cart updateCart = cart.get();
        updateCart.setCartCount(count);
        updateCart.setUpdateAt(LocalDateTime.now());
        cartRepository.save(updateCart);
        return Optional.ofNullable(convertToCartDto(updateCart));
    }

    @Override
    public Optional<CartDto> deleteCartById(Integer id) {
        Optional<Cart> cart =  cartRepository.findById(id);
        if(!cart.isPresent()) {
            return Optional.empty();
        }
        Cart cartToDelete = cart.get();
        cartRepository.delete(cartToDelete);
        return Optional.ofNullable(convertToCartDto(cartToDelete));
    }

    public CartDto convertToCartDto(Cart cart) {
        return CartDto.builder()
                .cartId(cart.getCartId())
                .cartCount(cart.getCartCount())
                .email(cart.getEmail())
                .product(cart.getProduct())
                .orderedAt(cart.getOrderedAt())
                .updateAt(cart.getUpdateAt())
                .build();
    }

}
