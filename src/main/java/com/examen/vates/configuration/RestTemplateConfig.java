package com.examen.vates.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final String apiKey = "1gJ5j2cZt1A8holjkLYk6HrAsEZaP9xt";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.rootUri("some uri")
                .additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
                    request.getHeaders().add("Content-Type","application/json");
                    request.getHeaders().add("apikey", apiKey);
                    return execution.execute(request, body);
                }).build();
    }
}
