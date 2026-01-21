package dev.nicolas.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("$(https://api.openai.com/v1/chat/completions)")
    private String openAiAPIUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(openAiAPIUrl).build();
    }

}
