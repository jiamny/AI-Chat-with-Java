package io.github.deepseek4j_chat;

import io.github.deepseek4j_chat.client.DeepSeekClient;
import io.github.deepseek4j_chat.client.DeepSeekApiException;
import io.github.deepseek4j_chat.client.ChatRequest;
import io.github.deepseek4j_chat.client.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DemoDeepSeek4jChat {
    private static final Logger logger = LoggerFactory.getLogger(DemoDeepSeek4jChat.class);
    public static void main(String[] args) {
        logger.info("Starting DeepSeek client example");

        DeepSeekClient client = DeepSeekClient.builder()
                .build();

        try {
            ChatRequest request = new ChatRequest();
            request.setModel("deepseek-r1:1.5b");
            request.setPrompt("Explain quantum computing in simple terms");
            request.setStream(false);

            logger.debug("Sending request: {}", request);
            ChatResponse response = client.chatCompletion(request);
            System.out.println("--------- isdone()= " + response.isDone());
            System.out.println("--------- getResponse()= " + response.getResponse());

        } catch (DeepSeekApiException e) {
            logger.error("API request failed: {}", e.getMessage(), e);
        } finally {
            try {
                client.close();
                logger.info("DeepSeek client closed successfully");
            } catch (IOException e) {
                logger.error("Failed to close client: {}", e.getMessage(), e);
            }
        }
        logger.info("DeepSeek client finished");
    }
}
