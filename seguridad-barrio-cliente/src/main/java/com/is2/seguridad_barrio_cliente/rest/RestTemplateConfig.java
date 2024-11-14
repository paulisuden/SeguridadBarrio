package com.is2.seguridad_barrio_cliente.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        TokenInterceptor tokenInterceptor = new TokenInterceptor();
        restTemplate.setInterceptors(Collections.singletonList(tokenInterceptor));
        return restTemplate;
    }
}
