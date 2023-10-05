package com.wojciech.barwinski.akbarrestapp;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Akbar school management system")
                        .version("1.0")
                        .description("Api for management data about school, employee etc for Akbar company.")
                        .contact(new Contact()
                                .name("Wojciech Barwiński")
                                //.url("http://me.com")
                                .email("wojciech.barwinski@gmail.com")));
    }
}
