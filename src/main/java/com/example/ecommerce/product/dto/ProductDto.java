package com.example.ecommerce.product.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
    private int productId;
    @NotBlank(message = "product name is required")
    private String name;
    @NotNull(message = "product price is required")
    @Min(value = 0, message = "price should be more then 0")
    private int price;
    @NotBlank(message = "product detail is required")
    private String detail;
    @NotNull(message = "product count is required")
    @Min(value = 1, message = "count should be more then 1")
    private int count;
    private LocalDateTime orderedAt;
    private LocalDateTime updateAt;
    @NotBlank(message = "product name is required")
    private String category;
}
