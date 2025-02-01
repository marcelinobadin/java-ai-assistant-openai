package io.fsaap.assistant.service;

import io.fsaap.assistant.dto.OpenAIRequest;
import io.fsaap.assistant.dto.OpenAIResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.Collections;

@ApplicationScoped
public class OpenAIIntegrationService {

    @Inject
    @RestClient
    OpenAIClient openAIClient;

    public OpenAIResponse sendChatMessage(String userMessage) {
        // Cria a requisição conforme a API da OpenAI
        OpenAIRequest request = new OpenAIRequest();
        request.setModel("gpt-3.5-turbo");

        OpenAIRequest.Message message = new OpenAIRequest.Message();
        message.setRole("user");
        message.setContent(userMessage);

        request.setMessages(Collections.singletonList(message));

        // Chama o endpoint e retorna a resposta
        return openAIClient.createChatCompletion(request);
    }
}
