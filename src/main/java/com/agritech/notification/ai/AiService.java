package com.agritech.notification.ai;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class AiService {

    private final RestClient restClient;

    public AiService() {

        String ollamaUrl = System.getenv()
                .getOrDefault(
                        "OLLAMA_BASE_URL",
                        "http://localhost:11434"
                );

        this.restClient = RestClient.builder()
                .baseUrl(ollamaUrl)
                .build();
    }

    public String generateNotification(String prompt) {

        Map<String, Object> request = Map.of(
                "model", "deepseek-r1:1.5b",
                "prompt", prompt,
                "stream", false
        );

        Map<?, ?> response = restClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(Map.class);

        if (response == null ||
                response.get("response") == null) {

            return "AI did not return a response.";
        }

        return response.get("response").toString();
    }
}