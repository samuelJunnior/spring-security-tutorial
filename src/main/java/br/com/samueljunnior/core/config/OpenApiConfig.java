package br.com.samueljunnior.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Exemplo com OAuth2")
                        .contact(new Contact()
                                .name("Samuel Junnior")
                                .url("https://samueljunnior.github.io/about-me/")
                        ).version("v1")
                )
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }

    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi
                .getPaths().values()
                .forEach(pathItem ->
                        pathItem.readOperations()
                                .forEach(operation ->
                                        operation.addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                                )
                );
    }

}
