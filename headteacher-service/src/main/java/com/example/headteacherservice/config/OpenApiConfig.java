package com.example.headteacherservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "oauth2",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "${security.oauth2.client.provider.keycloak.authorization-uri}",
                        tokenUrl = "${security.oauth2.client.provider.keycloak.token-uri}",
                        scopes = {
                                @OAuthScope(name = "openid"),
                                @OAuthScope(name = "profile"),
                                @OAuthScope(name = "email")
                        }
                )
        )
)
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tandem University API")
                        .description("API для управления тандемом"))
                .addSecurityItem(new SecurityRequirement().addList("oauth2"));
    }
}


