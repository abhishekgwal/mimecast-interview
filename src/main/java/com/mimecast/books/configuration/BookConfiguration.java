package com.mimecast.books.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
