package io.github.deepseek4j_chat.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a request for chat completion in DeepSeek API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {

    private String model;
    private String prompt;
    private boolean stream = false;

}
