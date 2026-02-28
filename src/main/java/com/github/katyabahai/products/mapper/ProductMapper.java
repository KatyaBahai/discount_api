package com.github.katyabahai.products.mapper;

import com.github.katyabahai.products.dto.BasicProductDto;
import com.github.katyabahai.products.dto.CreateProductDto;
import com.github.katyabahai.products.dto.DiscountedProductDto;
import com.github.katyabahai.products.model.Product;
import com.github.katyabahai.products.service.util.CalculateDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductMapper {
    private final CalculateDiscountService calculateDiscountService;

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

    public DiscountedProductDto basicToDiscounted(BasicProductDto basic) {
        return DiscountedProductDto.builder()
                .id(basic.getId())
                .name(basic.getName())
                .description(basic.getDescription())
                .category(basic.getCategory())
                .originalPrice(basic.getPrice())
                .discountedPrice(calculateDiscountService.calculateDiscount(basic))
                .createdAt(basic.getCreatedAt())
                .updatedAt(basic.getUpdatedAt())
                .build();
    }
}
