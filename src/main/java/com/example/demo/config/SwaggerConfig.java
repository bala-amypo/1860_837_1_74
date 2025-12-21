package com.example.demo.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        SecurityScheme scheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", scheme))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
