package com.github.katyabahai.products.service.util;

import com.github.katyabahai.products.config.DiscountConfig;
import com.github.katyabahai.products.dto.BasicProductDto;
import com.github.katyabahai.products.dto.DiscountedProductDto;
import com.github.katyabahai.products.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CalculateDiscountService {
    private final DiscountConfig discountConfig;

    public BigDecimal calculateDiscount(BasicProductDto basic) {
        return basic.getPrice().multiply(getDiscountMultiplier(basic.getCategory()));
    }

    private BigDecimal getDiscountMultiplier(Category category) {
        return switch (category) {
            case BOOKS -> BigDecimal.valueOf(discountConfig.getBooks());
            case ELECTRONICS -> BigDecimal.valueOf(discountConfig.getElectronics());
            case FOOD -> BigDecimal.valueOf(discountConfig.getFood());
            default -> BigDecimal.valueOf(discountConfig.getOther());
        };
    }
}
