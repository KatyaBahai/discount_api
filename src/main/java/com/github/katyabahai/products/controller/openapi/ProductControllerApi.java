package com.github.katyabahai.products.controller.openapi;

import com.github.katyabahai.products.dto.CreateProductDto;
import com.github.katyabahai.products.dto.DiscountedProductDto;
import com.github.katyabahai.products.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name = "ProductController", description = "Basic crud API for Product entities")
public interface ProductControllerApi {

    @Operation(
            summary = "Получить продукты с пагинацией и фильтрацией по категории",
            description = "Возвращает продукты с рассчитанными скидками. Поддерживает пагинацию и опционально фильтрацию по категории.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Успешно - пагинированный список продуктов",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class),
                                    examples = @ExampleObject("""
                                            {
                                              "content": [{
                                                "id": 1,
                                                "name": "iPhone",
                                                "description": "With advanced AI features",
                                                "originalPrice": 99999.99,
                                                "discountedPrice": 88800.99,
                                                "category": "ELECTRONICS",
                                                "createdAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]",
                                                "updatedAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]"
                                              }],
                                              "pageable": { "pageNumber": 0, "pageSize": 20 },
                                              "totalElements": 150,
                                              "totalPages": 8
                                            }
                                            """))),
                    @ApiResponse(responseCode = "400",
                            description = "Неверные параметры страницы/размера/сортировки")
            }
    )
    ResponseEntity<Page<DiscountedProductDto>> getProducts(@Parameter(description = "Фильтр по категории товара (необязательный параметр)",
            schema = @Schema(allowableValues = {"ELECTRONICS", "FOOD", "BOOKS", "OTHER"})) Category category,
                                                           @Parameter(description = "Номер страницы (начиная с 0)") int page,
                                                           @Parameter(description = "Количество товаров на странице") int size);

    @Operation(
            summary = "Получить продукт с рассчитанной скидкой по ID",
            description = "Возвращает информацию о продукте с учетом скидки."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешно - продукт найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DiscountedProductDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "id": 1,
                                        "name": "iPhone",
                                        "description": "With advanced AI features",
                                        "originalPrice": 99999.99,
                                        "discountedPrice": 88800.99,
                                        "category": "ELECTRONICS",
                                        "createdAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]",
                                        "updatedAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]"
                                      }
                                    """))),
            @ApiResponse(responseCode = "404",
                    description = "Продукт с указанным ID не найден")
    })
    ResponseEntity<DiscountedProductDto> getProduct(Long id);

    @Operation(
            summary = "Создать новый продукт",
            description = "Создает новый продукт. Поле description опционально. Цена должна не может быть < 0."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Продукт успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DiscountedProductDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "id": 1,
                                        "name": "iPhone",
                                        "description": "With advanced AI features",
                                        "originalPrice": 99999.99,
                                        "discountedPrice": 88800.99,
                                        "category": "ELECTRONICS",
                                        "createdAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]",
                                        "updatedAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]"
                                      }
                                    """))),
            @ApiResponse(responseCode = "400",
                    description = "Неверные данные: name пустой, price < 0, неверная категория")
    })
    ResponseEntity<DiscountedProductDto> createProduct(CreateProductDto dto);

    @Operation(
            summary = "Обновить существующий продукт",
            description = "Полное обновление продукта по ID. Все поля обязательны для обновления."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Продукт успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DiscountedProductDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "id": 1,
                                        "name": "iPhone",
                                        "description": "With advanced AI features",
                                        "originalPrice": 99999.99,
                                        "discountedPrice": 88800.99,
                                        "category": "ELECTRONICS"
                                        "createdAt": "2026-01-15T10:30:00+03:00[Europe/Moscow]"
                                        "updatedAt": "2026-01-15T12:30:00+03:00[Europe/Moscow]"
                                      }
                                    """))),
            @ApiResponse(responseCode = "400",
                    description = "Неверные данные в запросе"),
            @ApiResponse(responseCode = "404",
                    description = "Продукт с указанным ID не найден")
    })
    ResponseEntity<DiscountedProductDto> updateProduct(Long id, CreateProductDto dto);

    @Operation(
            summary = "Удалить продукт по ID",
            description = "Удаляет продукт из базы данных. Возвращает статус 204 без тела ответа."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404",
                    description = "Продукт с указанным ID не найден")
    })
    ResponseEntity<Void> deleteProduct(Long id);
}
