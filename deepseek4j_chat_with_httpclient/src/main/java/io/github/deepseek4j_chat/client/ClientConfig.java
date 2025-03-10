package io.github.deepseek4j_chat.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Configuration class that holds the API key and the base URL for client interactions.
 */
@Getter
@AllArgsConstructor
public class ClientConfig {

    /**
     * The base URL of the service API.
     */
    private final String baseUrl;
}
