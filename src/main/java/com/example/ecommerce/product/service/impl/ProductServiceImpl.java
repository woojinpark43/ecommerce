package com.example.ecommerce.product.service.impl;

import com.example.ecommerce.product.dto.ProductDto;
import com.example.ecommerce.product.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import com.example.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public boolean register(ProductDto productData){

        Product newProduct = Product.builder()
                .count(productData.getCount())
                .detail(productData.getDetail())
                .name(productData.getName())
                .orderedAt(productData.getOrderedAt())
                .price(productData.getPrice())
                .updateAt(productData.getUpdateAt())
                .category(productData.getCategory())
                .build();

        productRepository.save(newProduct);

        productData.setProductId(newProduct.getProductId());

        return true;
    }

    @Override
    public Page<Product> getAllProducts(Integer size) {
        return productRepository.findAll(PageRequest.of(0, size, Sort.Direction.ASC, "productId"));
    }

    @Override
    public List<String> getProductNamesByKeyword(String keyword) {
        Pageable limit = PageRequest.of(0, 10);
        Page<Product> productEntities = this.productRepository.findByNameStartingWithIgnoreCase(keyword, limit);
        return productEntities.stream()
                .map(e -> e.getName())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> getProductById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();

        return convertToProductDto(product);
    }

    @Override
    public Optional<ProductDto> updateProductById(Integer id, ProductDto productUpdate) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();
        product.setCategory(productUpdate.getCategory());
        product.setCount(productUpdate.getCount());
        product.setDetail(productUpdate.getDetail());
        product.setPrice(productUpdate.getPrice());
        product.setUpdateAt(LocalDateTime.now());
        product.setName(productUpdate.getName());

        productRepository.save(product);

        return convertToProductDto(product);
    }

    @Override
    public Optional<ProductDto> updateProductCount(Integer id, Integer count) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();
        product.setCount(count);
        productRepository.save(product);

        return convertToProductDto(product);
    }

    @Override
    public Optional<ProductDto> deleteProductById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();
        Optional<ProductDto> dto = convertToProductDto(product);
        productRepository.delete(product);

        return dto;
    }

    public Optional<ProductDto> convertToProductDto(Product product) {
        return Optional.ofNullable(ProductDto.builder()
                .category(product.getCategory())
                .count(product.getCount())
                .detail(product.getDetail())
                .name(product.getName())
                .orderedAt(product.getOrderedAt())
                .productId(product.getProductId())
                .price(product.getPrice())
                .updateAt(product.getUpdateAt())
                .build());
    }
}
