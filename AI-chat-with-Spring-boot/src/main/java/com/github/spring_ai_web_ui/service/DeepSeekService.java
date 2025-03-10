package com.github.spring_ai_web_ui.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import org.springframework.ai.ollama.OllamaChatModel;

import java.util.List;

@Service
public class DeepSeekService {
    @Autowired
    private OllamaApi ollamaapi;
    private final ChatClient chatClient;
    //private final OllamaApi ollamaapi = new OllamaApi("http://localhost:11434");
    private final OllamaOptions options = new OllamaOptions();

    public DeepSeekService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String getResponse(String prompt) {
        return chatClient.prompt(prompt) // Send the user's input to DeepSeek
                .call()       // Call the Ollama server
                .content();   // Extract the response
    }

    public Flux<String> getStreamingResponse(String prompt) {
        return chatClient.prompt(prompt).stream().content();
    }

    public String getModelResponse(String model, String prompt) {
        options.setModel(model);

        OllamaChatModel chatModel = new OllamaChatModel(ollamaapi, options,
                ToolCallingManager.builder().build(),
                io.micrometer.observation.ObservationRegistry.create(),
                ModelManagementOptions.defaults());
        String result = chatModel.call(prompt);
        return result;
    }

    public String getReachableModels( String checkModel) {
        String models = "";
        OllamaApi.ListModelResponse rs = ollamaapi.listModels();
        List<OllamaApi.Model> mds = rs.models();
        for(OllamaApi.Model md : mds) {
            models += md.model().toString() + "\t";
        }
        return models;
    }
}


