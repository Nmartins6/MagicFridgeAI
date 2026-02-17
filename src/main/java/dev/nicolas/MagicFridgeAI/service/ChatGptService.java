package dev.nicolas.MagicFridgeAI.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("OPENAI_API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe() {
        String prompt = "Teste de conexão";

        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "input", prompt
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {

                    var outputs = (List<Map<String, Object>>) response.get("output");

                    if (outputs == null || outputs.isEmpty()) {
                        return "Nenhuma receita foi gerada";
                    }

                    for (var output : outputs) {
                        var contents = (List<Map<String, Object>>) output.get("content");

                        if (contents == null || contents.isEmpty()) continue;

                        for (var content : contents) {
                            if ("output_text".equals(content.get("type"))) {
                                return (String) content.get("text");
                            }
                        }
                    }

                    return "Nenhuma receita foi gerada";
                });
    }
}