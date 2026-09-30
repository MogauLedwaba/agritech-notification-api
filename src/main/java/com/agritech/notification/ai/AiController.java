package com.agritech.notification.ai;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notifications/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public Map<String, String> generateNotification(
            @RequestBody AiRequest request) {

        String response = aiService.generateNotification(request.getPrompt());

        return Map.of("response", response);
    }
}