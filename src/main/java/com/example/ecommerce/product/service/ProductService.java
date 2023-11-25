package com.example.ecommerce.product.service;

import com.example.ecommerce.product.dto.ProductDto;
import com.example.ecommerce.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    boolean register(ProductDto productData);
    Page<Product> getAllProducts(Pageable pageable);
    List<String> getProductNamesByKeyword(String keyword);
    Optional<ProductDto> getProductById(Integer id);
    Optional<ProductDto> updateProductById(Integer id, ProductDto productUpdate);
    Optional<ProductDto> updateProductCount(Integer id, Integer count);
    Optional<ProductDto> deleteProductById(Integer id);
}
