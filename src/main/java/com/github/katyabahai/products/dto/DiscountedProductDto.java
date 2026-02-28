package com.github.katyabahai.products.dto;

import com.github.katyabahai.products.model.Category;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DiscountedProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private Category category;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
