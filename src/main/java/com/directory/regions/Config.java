package com.directory.regions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Config {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("REST API CRUD Regions Dir").version("1.0.1")
                .description("Basic CRUD rest app of the region directory. " + 
                             "Uses: Spring boot, H2 database, spring cache, slf4j, mybatis.")
                .contact(new Contact().name("Rifat Murtazin")
                .url("https://github.com/rifat-dev/restRegionsDir-myBatisCaching")));
    }
}
