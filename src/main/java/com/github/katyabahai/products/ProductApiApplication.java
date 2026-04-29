package com.github.katyabahai.products;

import com.github.katyabahai.products.config.DiscountConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableConfigurationProperties(DiscountConfig.class)
@EnableCaching
@EnableAsync
@SpringBootApplication
public class ProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.github.katyabahai.products.ProductApiApplication.class, args);
	}
}
