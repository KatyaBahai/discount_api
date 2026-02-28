# Discount API

API сервис для реализации подсчета скидки на продукт в зависимости от категории.

## Запуск проекта

1) Клонировать репозиторий:
```
git clone https://github.com/KatyaBahai/discount_api.git
```
2) Создать базу данных PostgreSQL:
```
CREATE DATABASE discount_api;
```
3) Открыть файл application.properties и указать логин и пароль от вашей базы данных:
```
url=jdbc:postgresql://localhost:5432/discount_api
username=<your-username>
password=<your-password>
```
4) Запустить приложение через основной класс ProductApiApplication или через maven:
```
mvn spring-boot:run
```
5) Эндпоинты будут доступны в Swagger: http://localhost:8080/swagger-ui/index.html

6) Скидки можно поменять в файле application.properties


## Эндпоинты API
```
POST /products — создать продукт
GET /products/{id} — получить продукт по id
PUT /products/{id} — обновить продукт
DELETE /products/{id} — удалить продукт
GET /products?page=&size=&category= — список с пагинацией и фильтрацией по
категории
```
## Пример тела запроса и ответа для POST /products:
```
{
"name": "Зачарованные",
"description": "Фанфик на сериал",
"price": 1200.00,
"category": "BOOKS"
}
Ответ: 201 Created

json
{
"id": 1,
"name": "Зачарованные",
"description": "Фанфик на сериал",
"price": 1200.00,
"priceWithDiscount": 1080.00,
"category": "BOOKS",
"createdAt": "2026-01-15T10:30:00Z",
"updatedAt": "2026-01-15T10:30:00Z"
}
```
## Пример запроса на получение списка продуктов с пагинацией и фильтрацией
GET /api/products?page=0&size=10&category=BOOKS

Параметры запроса:

page - номер страницы

size - размер страницы

category - фильтр по категории (опционально)

Ответ: 200 OK
```
{
"content": [
{
"id": 1,
"name": "Колобок",
"price": 1200.00,
"priceWithDiscount": 1080.00,
"category": "BOOKS"
},
{
"id": 2,
"name": "Курочка Ряба",
"price": 900.00,
"priceWithDiscount": 810.00,
"category": "BOOKS"
}
],
"pageable": {
"pageNumber": 0,
"pageSize": 10
},
"totalElements": 2,
"totalPages": 1
}
```
## Проверка эндпоинтов и HTTP статусов

Коды ошибок


- 200	Успешный запрос
- 201	Продукт создан
- 204	Продукт удалён
- 400	Ошибка валидации (некорректные данные)
- 404	Продукт не найден
- 500	Внутренняя ошибка сервера

## Пример ответа с ошибкой:
```
{
"timestamp": "2024-01-15T10:30:00Z",
"status": 404,
"error": "Not Found",
"message": "The product you're looking for cannot found. The id:67",
"path": "/api/products/67"
}
```


## Cтек технологий

Java 21

Spring Boot 3.x

Maven

PostgreSQL

Spring Data JPA

Lombok

OpenAPI/Swagger (документация API)

JUnit 5 / Mockito (тестирование)

Testcontainers (интеграционные тесты)