package com.github.katyabahai.products.service;

import com.github.katyabahai.products.exception.ProductNotFoundException;
import com.github.katyabahai.products.repository.ProductRepository;
import com.github.katyabahai.products.service.util.CalculateDiscountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasicProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CalculateDiscountService calculateDiscountService;
    @InjectMocks
    private BasicProductService productService;

    @Test
    @DisplayName("Should throw ProductNotFoundException when product with given ID doesn't exist")
    void whenGetProductWithInvalidIdThenThrowProductNotFoundException() {
        Long invalidId = 999L;
        when(productRepository.findDtoById(invalidId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getProductById(invalidId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining(invalidId.toString());

        verify(productRepository).findDtoById(invalidId);
        verify(calculateDiscountService, never()).calculateDiscount(any());
    }
}