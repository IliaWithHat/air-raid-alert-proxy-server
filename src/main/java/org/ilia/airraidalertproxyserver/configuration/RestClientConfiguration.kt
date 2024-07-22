package org.ilia.airraidalertproxyserver.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {

    @Bean
    fun restClient(
        @Value("\${api.url}") url: String,
        @Value("\${api.token}") apiToken: String
    ): RestClient {
        return RestClient.builder()
            .baseUrl(url)
            .defaultHeader("Authorization", "Bearer $apiToken")
            .build()
    }
}