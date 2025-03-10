package com.github.spring_ai_web_ui;


import org.junit.jupiter.api.Test;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@SpringBootTest(classes = LlmChatApplication.class)
public class TestOllama {

    //@Autowired
    private OllamaChatModel ollamaChatModel;
    //@Value("${spring.ai.ollama.base-url}")
    private String base_url;
    @Test
    public void testChatModel() {

        OllamaApi ollamaapi = new OllamaApi("http://localhost:11434");
        OllamaApi.ListModelResponse rs = ollamaapi.listModels();
        List<OllamaApi.Model> mds = rs.models();
        for(OllamaApi.Model md : mds) {
            System.out.println(md.toString());

            System.out.println(md.name());
            System.out.println("version = " + md.model().split(":")[1]);
            System.out.println(md.modifiedAt());
            System.out.println(md.digest());
            System.out.println(md.size());

            System.out.println(md.details().family());
            System.out.println(md.details().parameterSize());
            System.out.println(md.digest());
        }

        //System.out.println("default: " + ollamaChatModel.getDefaultOptions().getModel().toString());
        //System.out.println("default op: " + ollamaChatModel.getDefaultOptions().toString());

        OllamaOptions options = new OllamaOptions();
        options.setModel("deepseek-r1:latest");

        OllamaChatModel chatModel = new OllamaChatModel(ollamaapi, options,
                ToolCallingManager.builder().build(),
                io.micrometer.observation.ObservationRegistry.create(),
                ModelManagementOptions.defaults());

        String prompt = """
                你是一个精通中文和英文的翻译大师。如果我给你英文就翻译成中文，给你中文就翻译成英文。
                """;
        String message = """
                Ollama now supports tool calling with popular models such as Llama 3.1.
                This enables a model to answer a given prompt using tool(s) it knows about,
                making it possible for models to perform more complex tasks or interact with the outside world.
                """;
        String prp = "Explain quantum computing in simple term";

        String result = chatModel.call(prp); //prompt + ":" + message);
        System.out.println(result);
        System.out.println("current model: " + chatModel.getDefaultOptions().getModel().toString());
        System.out.println("current op: " + chatModel.getDefaultOptions().toString());

    }
}
