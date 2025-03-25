package com.blanco.interbanking.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Interbanking challenge")
                        .description("Administrator of adhered companies")
                        .version("1.0")
                        .contact(new Contact().email("federicoblanco1986@gmail.com")
                                .name("Federico Blanco")
                                .url("https://github.com/ingblanco")
                        )
                )
                .servers(List.of(new Server().url("/")));
    }

}
