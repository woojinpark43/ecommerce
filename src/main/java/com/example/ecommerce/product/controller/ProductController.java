package com.example.ecommerce.product.controller;

import com.example.ecommerce.error.ErrorUtility;
import com.example.ecommerce.product.dto.ProductDto;
import com.example.ecommerce.product.entity.Product;
import com.example.ecommerce.product.exception.ProductNotFoundException;
import com.example.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ErrorUtility errorUtility;

    @PostMapping("/product/register")
    public ResponseEntity<?> register(@RequestBody @Valid ProductDto productRegister, Errors errors) {

        if (errors.hasErrors()) {
            return errorUtility.errorValidationResponseEntity(errors);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        productRegister.setOrderedAt(currentDateTime);
        productRegister.setUpdateAt(currentDateTime);
        boolean registerSuccess = productService.register(productRegister);

        if(!registerSuccess) {
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(productRegister);
    }

    @GetMapping("/product/items")
    public ResponseEntity<?> getItems(final Pageable pageable) {
        Page<Product> result = productService.getAllProducts(pageable);
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handlerProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/product/items/{id}")
    public ResponseEntity<?> getItemsById(@PathVariable Integer id) {
        ProductDto product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("product with id not found."));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/items/{id}")
    public ResponseEntity<?> updateItemsById(@PathVariable Integer id, @RequestBody @Valid ProductDto productUpdate
            , Errors errors) {

        if (errors.hasErrors()) {
            return errorUtility.errorValidationResponseEntity(errors);
        }

        ProductDto product = productService.updateProductById(id, productUpdate)
                .orElseThrow(() -> new ProductNotFoundException("product with id not found."));
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/product/items/{id}/count")
    public ResponseEntity<?> productCountUpdate(@PathVariable Integer id,
                                                @RequestBody Integer count) {
        ProductDto result = productService.updateProductCount(id, count)
                .orElseThrow(() -> new ProductNotFoundException("product with id not found."));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/product/items/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        ProductDto result = productService.deleteProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("product with id not found."));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/product/search")
    public ResponseEntity<?> searchKeyword(@RequestParam String search) {
        List<String> result = productService.getProductNamesByKeyword(search);
        return ResponseEntity.ok(result);
    }
}
