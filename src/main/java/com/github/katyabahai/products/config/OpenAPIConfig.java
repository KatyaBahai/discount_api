package com.github.katyabahai.products.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Profile;

//@EnableOpenApi
@Configuration
public class OpenAPIConfig {

    @Value("${discount_api.openapi.dev-url}")
    private String devUrl;

    @Value("${discount_api.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL сервера в режиме разработки");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("URL сервера в режиме продакшн");

        Contact contact = new Contact();
        contact.setEmail("katyxa-two@yandex.ru");
        contact.setName("Katie");

        License mitLicense = new License().name("MIT License").url("https://licensetemplate.com/licenses/mit/");

        Info info = new Info()
                .title("Продукты со скидками")
                .version("1.0")
                .contact(contact)
                .description("Этот API предоставляет эндпоинты для управления проектом discount_api.")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}
