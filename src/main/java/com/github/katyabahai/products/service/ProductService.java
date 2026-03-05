package com.github.katyabahai.products.service;

import com.github.katyabahai.products.dto.CreateProductDto;
import com.github.katyabahai.products.dto.DiscountedProductDto;

import com.github.katyabahai.products.model.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<DiscountedProductDto> findAllDtos(Category category, Pageable pageable);

    DiscountedProductDto getProductById(Long id);

    DiscountedProductDto createProduct(CreateProductDto createDto);

    DiscountedProductDto updateProduct(Long id, CreateProductDto updateDto);

    void deleteProduct(Long id);
}
