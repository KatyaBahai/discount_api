package com.github.katyabahai.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BasicProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
