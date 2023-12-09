package com.example.ecommerce.cart.controller;

import com.example.ecommerce.cart.dto.CartDto;
import com.example.ecommerce.cart.dto.CartRegister;
import com.example.ecommerce.cart.exception.EmailNotFoundException;
import com.example.ecommerce.cart.exception.InvalidCartId;
import com.example.ecommerce.cart.service.CartService;
import com.example.ecommerce.error.ErrorUtility;
import com.example.ecommerce.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/register")
    public ResponseEntity<?> register(@RequestBody @Valid CartRegister cart, Errors errors) {

        if (errors.hasErrors()) {
            return ErrorUtility.errorValidationResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        boolean registerSuccess = cartService.register(cart);

        if(!registerSuccess) {
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(cart);
    }

    @GetMapping("/cart/user/items/search")
    public ResponseEntity<?> getItemsByEmail(@RequestBody String email) {
        List<CartDto> cart = cartService.getCartByEmail(email);
        if(cart.size() == 0) {
            throw new EmailNotFoundException("user with email not found.");
        }
        return ResponseEntity.ok(cart);
    }

    @PatchMapping("/cart/update/count/{id}")
        public ResponseEntity<?> updateCartCountById(@PathVariable Integer id, @RequestBody Integer count) {
        Optional<CartDto> cart = cartService.updateCountById(id, count);
        if(!cart.isPresent()) {
            throw new InvalidCartId("This cart id is invalid.");
        }
        return ResponseEntity.ok(cart);
    }

    @PatchMapping("/cart/delete/{id}")
    public ResponseEntity<?> deleteCartById(@PathVariable Integer id) {
        Optional<CartDto> cart = cartService.deleteCartById(id);
        if(!cart.isPresent()) {
            throw new InvalidCartId("This cart id is invalid.");
        }
        return ResponseEntity.ok(cart);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handlerEmailNotFoundException(EmailNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCartId.class)
    public ResponseEntity<?> handlerInvalidCartId(InvalidCartId exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
