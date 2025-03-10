package com.github.spring_ai_web_ui.controller;

import com.github.spring_ai_web_ui.service.DeepSeekService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class ChatController {

    private final DeepSeekService deepSeekService;

    public ChatController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @GetMapping("/prompt")
    public String chatResponse(@RequestParam(value = "question") String question) {
        return deepSeekService.getResponse(question);
    }

    // Receive Response from DeepSeek
    @GetMapping("/prompt/stream")
    public Flux<String> chatStream(@RequestParam(value = "question") String question) {
        return deepSeekService.getStreamingResponse(question);
    }

    // Receive Response based on model and prompt
    @RequestMapping(value = "/model-based")
    public String chatModelResponse(@RequestParam Map<String,String> requestParams) {
        String model = requestParams.get("model");
        String prompt = requestParams.get("question");
        return deepSeekService.getModelResponse(model, prompt);
    }

    @GetMapping("/models")
    public String chatReachableModels(@RequestParam(value = "modelinfo") String modelinfo) {
        return deepSeekService.getReachableModels(modelinfo);
    }
}

