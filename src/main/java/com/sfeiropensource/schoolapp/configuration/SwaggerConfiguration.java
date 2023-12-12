package com.sfeiropensource.schoolapp.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Value("${info.api.title}")
    private String apiTitle;

    @Value("${info.api.version}")
    private String apiVersion;

    @Value("${info.api.description}")
    private String apiDescription;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title(apiTitle).description(apiDescription).version(apiVersion)
        ).components(
                new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                        )
        );
    }
}