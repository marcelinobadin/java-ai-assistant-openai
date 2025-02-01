package io.fsaap.assistant.service;

import io.fsaap.assistant.domain.Assistant;
import io.fsaap.assistant.domain.ConversationThread;
import io.fsaap.assistant.domain.Message;
import io.fsaap.assistant.domain.OpenAIResponse;
import io.fsaap.assistant.domain.TelegramMessageEvent;
import io.fsaap.assistant.dto.MessageRequest;
import io.fsaap.assistant.dto.MessageResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class AssistantService {

    private Map<String, ConversationThread> threads = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    @Inject
    OpenAIService openAIService;

    @Inject
    Event<TelegramMessageEvent> telegramEvent;

    public MessageResponse processMessage(MessageRequest request) {
        ConversationThread thread;
        if (request.getThreadId() == null || request.getThreadId().trim().isEmpty()) {
            String threadId = UUID.randomUUID().toString();
            Assistant assistant = new Assistant(request.getAssistantId());
            thread = new ConversationThread(threadId, assistant);
            threads.put(threadId, thread);
        } else {
            thread = threads.get(request.getThreadId());
            if (thread == null) {
                Assistant assistant = new Assistant(request.getAssistantId());
                thread = new ConversationThread(request.getThreadId(), assistant);
                threads.put(request.getThreadId(), thread);
            }
        }

        Message newMessage = new Message(UUID.randomUUID().toString(), "user", request.getMessage());
        thread.getMessages().add(newMessage);

        ScheduledFuture<?> future = thread.getTimerFuture();
        if (future != null && !future.isDone()) {
            future.cancel(false);
        }
        ConversationThread finalThread = thread;
        ScheduledFuture<Void> newFuture = scheduler.schedule(() -> {
            processThread(finalThread);
            return null;
        }, 10, TimeUnit.SECONDS);
        thread.setTimerFuture(newFuture);

        return new MessageResponse(thread.getAssistant().getId(), thread.getId());
    }

    private void processThread(ConversationThread thread) {
        boolean finished = false;
        while (!finished) {
            OpenAIResponse response = openAIService.runThread(thread);
            if (response.isFunctionCall()) {
                String functionResult = executeFunction(response.getFunctionName(), response.getFunctionParams());
                thread.getMessages().add(new Message(UUID.randomUUID().toString(), "system", "Resultado: " + functionResult));
                openAIService.sendFunctionResult(thread, functionResult);
            } else {
                finished = true;
                String finalMessage = openAIService.getLastMessage(thread);
                telegramEvent.fire(new TelegramMessageEvent(thread.getId(), thread.getAssistant().getId(), finalMessage));
            }
        }
        threads.remove(thread.getId());
    }

    private String executeFunction(String functionName, Map<String, Object> params) {
        return "Função '" + functionName + "' executada com parâmetros " + params;
    }
}
