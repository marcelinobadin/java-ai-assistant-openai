package io.fsaap.assistant.service;

import io.fsaap.assistant.domain.ConversationThread;
import io.fsaap.assistant.domain.Message;
import io.fsaap.assistant.domain.OpenAIResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class OpenAIService {

    // Simula a execução da thread na OpenAI.
    public OpenAIResponse runThread(ConversationThread thread) {
        Message lastMessage = thread.getMessages().get(thread.getMessages().size() - 1);
        if (lastMessage.getText().toLowerCase().contains("function")) {
            Map<String, Object> params = new HashMap<>();
            params.put("param1", "value1");
            return new OpenAIResponse(true, "sampleFunction", params, null);
        } else {
            return new OpenAIResponse(false, null, null, "Resposta final para a thread " + thread.getId());
        }
    }

    // Simula o envio do resultado da função para a OpenAI.
    public void sendFunctionResult(ConversationThread thread, String functionResult) {
        thread.getMessages().add(new Message(UUID.randomUUID().toString(), "system", "Resultado da função enviado: " + functionResult));
    }

    // Simula a obtenção da última mensagem da thread.
    public String getLastMessage(ConversationThread thread) {
        return thread.getMessages().get(thread.getMessages().size() - 1).getText();
    }
}
