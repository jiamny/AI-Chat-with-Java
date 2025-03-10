package com.github.spring_ai_web_ui.config;

import org.springframework.ai.autoconfigure.ollama.OllamaConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class OllamaApiConfig {

    @Bean
    @Qualifier("OllamaRestClientBuilder")
    public RestClient.Builder OllamaRestClientBuilder() {
        // set HTTP custom port
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(120))
                .build();

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(Duration.ofMinutes(5));

        return RestClient.builder().requestFactory(requestFactory);
    }

    @Bean
    public OllamaApi ollamaApi(OllamaConnectionDetails connectionDetails,
        @Qualifier("OllamaRestClientBuilder") RestClient.Builder restClientBuilder,
        WebClient.Builder webClientBuilder) {
        return new OllamaApi(connectionDetails.getBaseUrl(), restClientBuilder, webClientBuilder);
    }
}
