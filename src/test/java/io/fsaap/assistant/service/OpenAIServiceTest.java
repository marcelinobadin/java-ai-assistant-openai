package io.fsaap.assistant.service;

import io.fsaap.assistant.domain.Assistant;
import io.fsaap.assistant.domain.ConversationThread;
import io.fsaap.assistant.domain.Message;
import io.fsaap.assistant.domain.OpenAIResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class OpenAIServiceTest {

    @Inject
    OpenAIService openAIService;

    @Test
    void testRunThreadSemFunctionCall() {
        // Cria uma thread com mensagem sem a palavra "function"
        Assistant assistant = new Assistant("assistant-1");
        ConversationThread thread = new ConversationThread("thread-1", assistant);
        thread.getMessages().add(new Message("msg-1", "user", "Olá, tudo bem?"));

        OpenAIResponse response = openAIService.runThread(thread);
        assertFalse(response.isFunctionCall());
        assertNotNull(response.getMessage());
        assertEquals("Resposta final para a thread " + thread.getId(), response.getMessage());
    }

    @Test
    void testRunThreadComFunctionCall() {
        // Cria uma thread com mensagem contendo a palavra "function"
        Assistant assistant = new Assistant("assistant-1");
        ConversationThread thread = new ConversationThread("thread-2", assistant);
        thread.getMessages().add(new Message("msg-2", "user", "Por favor, execute function"));

        OpenAIResponse response = openAIService.runThread(thread);
        assertTrue(response.isFunctionCall());
        assertEquals("sampleFunction", response.getFunctionName());
        assertNotNull(response.getFunctionParams());
        assertNull(response.getMessage());
    }
}
