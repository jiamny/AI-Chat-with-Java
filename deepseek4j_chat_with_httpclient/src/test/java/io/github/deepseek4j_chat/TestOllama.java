package io.github.deepseek4j_chat;

import org.junit.jupiter.api.Test;
import io.github.deepseek4j_chat.client.DeepSeekClient;
import io.github.deepseek4j_chat.client.DeepSeekApiException;
import io.github.deepseek4j_chat.client.ChatRequest;
import io.github.deepseek4j_chat.client.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestOllama {

    @Test
    public void testDeepseek4j_chat() {
        Logger logger = LoggerFactory.getLogger(TestOllama.class);
        logger.info("Starting DeepSeek client example");
        DeepSeekClient client = DeepSeekClient.builder()
                .build();

        String prompt = """
                你是一个精通中文和英文的翻译大师。如果我给你英文就翻译成中文，给你中文就翻译成英文。
                """;
        String message = """
                Ollama now supports tool calling with popular models such as Llama 3.1.
                This enables a model to answer a given prompt using tool(s) it knows about,
                making it possible for models to perform more complex tasks or interact with the outside world.
                """;

        try {
            ChatRequest request = new ChatRequest();
            request.setModel("deepseek-r1:1.5b");
            request.setPrompt(prompt + ":" + message); //"Explain quantum computing in simple terms");
            request.setStream(false);

            logger.debug("Sending request: {}", request);
            ChatResponse response = client.chatCompletion(request);
            System.out.println("isdone()= " + response.isDone());
            System.out.println("getResponse()= " + response.getResponse());

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
    }
}