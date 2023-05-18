package ua.nure.makestart.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI obminyashkaOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Make start API")
                        .description("API Definitions of the Make start project")
                        .version("v0.1.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Make start GitHub Docs")
                        .url("https://github.com/ennnbaev/make-start"));
    }
}
