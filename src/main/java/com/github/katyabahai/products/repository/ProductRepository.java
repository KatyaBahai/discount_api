package com.github.katyabahai.products.repository;

import com.github.katyabahai.products.dto.BasicProductDto;
import com.github.katyabahai.products.model.Category;
import com.github.katyabahai.products.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT new com.github.katyabahai.products.dto.BasicProductDto
            (p.id, p.name, p.description, p.price, p.category, p.createdAt, p.updatedAt)
            FROM Product p WHERE (p.id = :id)
            """)
    Optional<BasicProductDto> findDtoById(@Param("id") Long id);

    @Query("""
            SELECT new com.github.katyabahai.products.dto.BasicProductDto
            (p.id, p.name, p.description, p.price, p.category, p.createdAt, p.updatedAt)
            FROM Product p WHERE (:category IS NULL OR p.category = :category)
            """)
    Page<BasicProductDto> findAllDtos(@Param("category") Category category, Pageable pageable);

    boolean existsById(Long id);
}
