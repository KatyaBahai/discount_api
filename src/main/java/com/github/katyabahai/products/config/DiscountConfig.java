package com.github.katyabahai.products.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.discount")
@Component
@Data
public class DiscountConfig {
    private double books;
    private double electronics;
    private double food;
    private double other;
}
