package com.github.katyabahai.products.dto;

import com.github.katyabahai.products.model.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateProductDto {
    @NotBlank(message = "The name is required")
    @Size(min = 1, max = 255, message = "The name must be between 1 and 255 characters")
    private String name;

    @Size(max = 1000, message = "The description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "The price cannot be negative")
    private BigDecimal price;

    @NotNull(message = "The category is required")
    private Category category;
}
