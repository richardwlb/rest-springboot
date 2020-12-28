package br.com.richard.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // Acesso padrão a documentação:
    // http://localhost:8080/swagger-ui.html

    // Para exportar pro postman:
    // http://localhost:8080/v2/api-docs
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.richard"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
            "RESTFull API with Spring Boot 2.1.3 - Foo Bar",
            "Description about the API",
            "v1",
            "Terms of service url",
            new Contact("Richard Brehmer", "www.rbrehmer.com", "email@gmail.com"),
            "License of API","License of URL", Collections.emptyList()
        );
    }
}
