package com.nisum.nisumtest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition (
        info = @Info(
                title = "Nisum Tests",
                version = "1.0.0",
                description = "Este es un proyecto de prueba para Nisum"
        )
)
public class OpenApiConfig {


}
