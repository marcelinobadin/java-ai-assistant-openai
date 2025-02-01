package io.fsaap.assistant.service;

import io.fsaap.assistant.dto.AssistantConversationResponse;
import io.fsaap.assistant.dto.AssistantMessageRequest;
import io.fsaap.assistant.dto.AssistantRunResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class OpenAIAssistantIntegrationService {

    @RestClient
    OpenAIAssistantClient openAIAssistantClient;

    /**
     * Cria uma nova conversa no assistente com a mensagem inicial.
     * @param assistantId o id do assistente (definido na sua conta OpenAI)
     * @param senderId o id do remetente
     * @param message a mensagem inicial
     * @return a resposta contendo o conversationId
     */
    public AssistantConversationResponse createNewConversation(String assistantId, String senderId, String message) {
        AssistantMessageRequest req = new AssistantMessageRequest();
        req.setSenderId(senderId);
        req.setMessage(message);
        return openAIAssistantClient.createConversation(assistantId, req);
    }

    /**
     * Adiciona uma mensagem a uma conversa existente.
     * @param assistantId o id do assistente
     * @param conversationId o id da conversa
     * @param senderId o id do remetente
     * @param message a mensagem a ser adicionada
     */
    public void sendMessage(String assistantId, String conversationId, String senderId, String message) {
        AssistantMessageRequest req = new AssistantMessageRequest();
        req.setSenderId(senderId);
        req.setMessage(message);
        openAIAssistantClient.addMessage(assistantId, conversationId, req);
    }

    /**
     * Executa (run) a conversa para processar as mensagens e obter uma resposta.
     * @param assistantId o id do assistente
     * @param conversationId o id da conversa
     * @return a resposta do assistente após o run
     */
    public AssistantRunResponse runConversation(String assistantId, String conversationId) {
        return openAIAssistantClient.runConversation(assistantId, conversationId);
    }
}
