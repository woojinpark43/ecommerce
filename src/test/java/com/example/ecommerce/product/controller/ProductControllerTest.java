package com.example.ecommerce.product.controller;

import com.example.ecommerce.product.dto.ProductDto;
import com.example.ecommerce.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @MockBean
    private Errors errors;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successRegisterProduct() throws Exception {
        //given
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 11, 29, 12, 30);
        ProductDto mockProductDto = ProductDto.builder()
        .productId(1)
        .category("housing")
        .count(1)
        .detail("towel")
        .name("towel 45")
        .orderedAt(specificDateTime)
        .updateAt(specificDateTime)
        .price(1)
        .build();

        given(errors.hasErrors())
                .willReturn(false);

        given(productService.getProductById(1))
                .willReturn(java.util.Optional.ofNullable(mockProductDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/items/1"))
                .andDo(print())
                .andExpect(jsonPath("$.productId").value("1"))
                .andExpect(jsonPath("$.category").value("housing"))
                .andExpect(jsonPath("$.count").value("1"))
                .andExpect(jsonPath("$.detail").value("towel"))
                .andExpect(jsonPath("$.name").value("towel 45"))
                .andExpect(status().isOk());
    }

}