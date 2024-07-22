package org.ilia.airraidalertproxyserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Bean
    public RestClient restClient(@Value("${api.url}") String url,
                                 @Value("${api.token}") String apiToken) {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Authorization", "Bearer " + apiToken)
                .build();
    }
}
