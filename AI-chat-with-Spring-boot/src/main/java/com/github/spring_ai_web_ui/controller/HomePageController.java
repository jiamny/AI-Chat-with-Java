package com.github.spring_ai_web_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping
    public String home(){
        return "chat";
    }
}
