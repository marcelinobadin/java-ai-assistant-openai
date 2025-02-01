package io.fsaap.assistant.service;

import io.fsaap.assistant.dto.MessageRequest;
import io.fsaap.assistant.dto.MessageResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AssistantServiceTest {

    @Inject
    AssistantService assistantService;

    @Test
    void testProcessMessageCriaNovaThread() {
        MessageRequest request = new MessageRequest();
        request.setAssistantId("assistant-1");
        request.setMessage("Olá, estou testando");
        request.setThreadId(null);

        MessageResponse response = assistantService.processMessage(request);
        assertNotNull(response);
        assertEquals("assistant-1", response.getAssistantId());
        assertNotNull(response.getThreadId());
        assertFalse(response.getThreadId().isEmpty());
    }

    @Test
    void testProcessMessageComThreadExistente() {
        // Cria uma nova thread
        MessageRequest request1 = new MessageRequest();
        request1.setAssistantId("assistant-1");
        request1.setMessage("Primeira mensagem");
        request1.setThreadId(null);

        MessageResponse response1 = assistantService.processMessage(request1);
        String threadId = response1.getThreadId();

        // Envia outra mensagem para a mesma thread
        MessageRequest request2 = new MessageRequest();
        request2.setAssistantId("assistant-1");
        request2.setMessage("Segunda mensagem");
        request2.setThreadId(threadId);

        MessageResponse response2 = assistantService.processMessage(request2);
        assertEquals(threadId, response2.getThreadId());
    }
}
