package com.github.katyabahai.products.service;

import com.github.katyabahai.products.dto.BasicProductDto;
import com.github.katyabahai.products.dto.CreateProductDto;
import com.github.katyabahai.products.dto.DiscountedProductDto;
import com.github.katyabahai.products.exception.ProductNotFoundException;
import com.github.katyabahai.products.mapper.ProductMapper;
import com.github.katyabahai.products.model.Category;
import com.github.katyabahai.products.model.Product;
import com.github.katyabahai.products.repository.ProductRepository;
import com.github.katyabahai.products.service.util.CalculateDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasicProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CalculateDiscountService calculateDiscountService;
    private final ProductMapper productMapper;

    @Transactional
    public Page<DiscountedProductDto> findAllDtos(Category category, Pageable pageable) {
        Page<BasicProductDto> productDtosPage = productRepository.findAllDtos(category, pageable);
        return productDtosPage.map(calculateDiscountService::calculateDiscountedPrice);
    }

    public DiscountedProductDto getProductById(Long id) {
        BasicProductDto basic = productRepository.findDtoById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return calculateDiscountService.calculateDiscountedPrice(basic);
    }

    public DiscountedProductDto createProduct(CreateProductDto createDto) {
        Product product = productMapper.toEntity(createDto);
        Product savedProduct = productRepository.save(product);
        BasicProductDto basic = productMapper.toBasicDto(savedProduct);
        return calculateDiscountService.calculateDiscountedPrice(basic);
    }

    public DiscountedProductDto updateProduct(Long id, CreateProductDto updateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productMapper.updateEntityFromDto(updateDto, product);
        Product savedProduct = productRepository.save(product);

        BasicProductDto basic = productMapper.toBasicDto(savedProduct);
        return calculateDiscountService.calculateDiscountedPrice(basic);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
