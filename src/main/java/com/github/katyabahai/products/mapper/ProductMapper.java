package com.github.katyabahai.products.mapper;

import com.github.katyabahai.products.dto.BasicProductDto;
import com.github.katyabahai.products.dto.CreateProductDto;
import com.github.katyabahai.products.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .build();
    }

    public void updateEntityFromDto(CreateProductDto dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
    }

    public BasicProductDto toBasicDto(Product product) {
        return new BasicProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
