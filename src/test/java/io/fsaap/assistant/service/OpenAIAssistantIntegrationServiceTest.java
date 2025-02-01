package io.fsaap.assistant.service;

import io.fsaap.assistant.dto.AssistantConversationResponse;
import io.fsaap.assistant.dto.AssistantMessageRequest;
import io.fsaap.assistant.dto.AssistantRunResponse;
import io.quarkus.test.InjectMock;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@QuarkusTest
class OpenAIAssistantIntegrationServiceTest {

    @InjectMock
    @RestClient
    OpenAIAssistantClient openAIAssistantClient;

    @Inject
    OpenAIAssistantIntegrationService integrationService;

    @Test
    void testCreateNewConversation() {
        // Arrange
        AssistantConversationResponse response = new AssistantConversationResponse();
        response.setConversationId("conv-123");

        when(openAIAssistantClient.createConversation(eq("assistant-1"), any(AssistantMessageRequest.class)))
            .thenReturn(response);

        // Act
        AssistantConversationResponse result = integrationService.createNewConversation("assistant-1", "user-1", "Olá assistente!");

        // Assert
        assertNotNull(result, "A resposta não deve ser nula");
        assertEquals("conv-123", result.getConversationId(), "O conversationId deve ser 'conv-123'");
    }

    @Test
    void testSendMessage() {
        // Arrange: Para métodos void, usaremos o verify para confirmar a invocação.
        doNothing().when(openAIAssistantClient).addMessage(eq("assistant-1"), eq("conv-123"), any(AssistantMessageRequest.class));

        // Act
        integrationService.sendMessage("assistant-1", "conv-123", "user-1", "Nova mensagem");

        // Assert
        verify(openAIAssistantClient, times(1))
            .addMessage(eq("assistant-1"), eq("conv-123"), any(AssistantMessageRequest.class));
    }

    @Test
    void testRunConversation() {
        // Arrange
        AssistantRunResponse runResponse = new AssistantRunResponse();
        runResponse.setStatus("finished");
        runResponse.setResultMessage("Resposta do assistente");

        when(openAIAssistantClient.runConversation("assistant-1", "conv-123"))
            .thenReturn(runResponse);

        // Act
        AssistantRunResponse result = integrationService.runConversation("assistant-1", "conv-123");

        // Assert
        assertNotNull(result, "A resposta do run não deve ser nula");
        assertEquals("finished", result.getStatus(), "O status deve ser 'finished'");
        assertEquals("Resposta do assistente", result.getResultMessage(), "O resultado deve ser 'Resposta do assistente'");
    }
}