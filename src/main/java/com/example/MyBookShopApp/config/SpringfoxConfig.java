package com.example.MyBookShopApp.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SpringfoxConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.example.MyBookShopApp.controllers"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //.paths(PathSelectors.ant("/api/*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo(){
        return new ApiInfo(
                "Bookshop API",
                "API для нового книжного магазина",
                "1.0",
                "http://example.com",
                new Contact("Владелец API", "URL владельца", "email@email.com"),
                "api_license",
                "Тут URL лицензии",
                new ArrayList<>()
        );
    }
}
