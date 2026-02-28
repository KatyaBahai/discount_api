package com.github.katyabahai.products.controller;

import com.github.katyabahai.products.dto.CreateProductDto;
import com.github.katyabahai.products.dto.DiscountedProductDto;
import com.github.katyabahai.products.model.Category;
import com.github.katyabahai.products.service.BasicProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final BasicProductService productService;

    @GetMapping
    public ResponseEntity<Page<DiscountedProductDto>> getProducts(
            @RequestParam(required = false) Category category,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<DiscountedProductDto> Productspage = productService.findAllDtos(category, pageable);
        return ResponseEntity.ok(Productspage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountedProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<DiscountedProductDto> createProduct(
            @Valid @RequestBody CreateProductDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountedProductDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody CreateProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
