package io.fsaap.assistant.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

public class ConversationThread {
    private final String id;
    private final Assistant assistant;
    private final List<Message> messages = new ArrayList<>();
    private ScheduledFuture<Void> timerFuture;

    public ConversationThread(String id, Assistant assistant) {
        this.id = id;
        this.assistant = assistant;
    }

    public String getId() {
        return id;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public ScheduledFuture<Void> getTimerFuture() {
        return timerFuture;
    }

    public void setTimerFuture(ScheduledFuture<Void> timerFuture) {
        this.timerFuture = timerFuture;
    }
}
