package com.github.katyabahai.products.controller;

import com.github.katyabahai.products.config.ContainersConfig;
import com.github.katyabahai.products.model.Category;
import com.github.katyabahai.products.model.Product;
import com.github.katyabahai.products.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIT extends ContainersConfig {
    private final MockMvc mockMvc;
    private final ProductRepository productRepository;

    @Autowired
    public ProductControllerIT(ProductRepository productRepository,
                               MockMvc mockMvc) {
        this.productRepository = productRepository;
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("GET /products/{id} - returns product from PostgreSQL")
    void whenGetProductThenSuccess() throws Exception {

        Product product = new Product();
        product.setName("iPhone 15");
        product.setPrice(BigDecimal.valueOf(999.99));
        product.setCategory(Category.ELECTRONICS);
        productRepository.save(product);
        Long productId = product.getId();

        mockMvc.perform(get("/products/{id}", productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("iPhone 15"))
                .andExpect(jsonPath("$.category").value("ELECTRONICS"));
    }
}
