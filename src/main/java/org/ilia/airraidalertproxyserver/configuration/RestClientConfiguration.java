package org.ilia.airraidalertproxyserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Bean
    public RestClient restClient(@Value("${api.token}") String apiToken) {
        return RestClient.builder()
                .baseUrl("https://api.alerts.in.ua")
                .defaultHeader("Authorization", "Bearer " + apiToken)
                .build();
    }
}
