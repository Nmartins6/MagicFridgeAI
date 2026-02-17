package dev.nicolas.MagicFridgeAI.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final String apiKey = System.getenv("OPENAI_API_KEY");

    public ChatGptService(WebClient.Builder builder) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("OPENAI_API_KEY não está definida no ambiente.");
        }

        this.webClient = builder
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    public Mono<String> generateRecipe() {
        String prompt = "Verificando conexão";

        Map<String, Object> body = Map.of(
                "model", "gpt-4.1",
                "input", prompt
        );

        return webClient.post()
                .uri("/responses")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }
}