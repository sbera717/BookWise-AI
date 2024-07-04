package com.example.MinorProject.DigitalLibrary.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        Contact myContact = new Contact();
        myContact.setName("Subrat Bera");
        myContact.setEmail("sbera717@gmail.com");

        Info information = new Info()
                .title("BookWise AI")
                .version("1.0")
                .description("An innovative AI-powered library management system that offers bibliotherapy")
                .contact(myContact);
        return new OpenAPI().info(information);
    }


}
