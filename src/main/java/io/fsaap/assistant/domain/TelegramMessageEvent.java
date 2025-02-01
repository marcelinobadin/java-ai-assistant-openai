package io.fsaap.assistant.domain;

public class TelegramMessageEvent {
    private String threadId;
    private String assistantId;
    private String message;

    public TelegramMessageEvent(String threadId, String assistantId, String message) {
        this.threadId = threadId;
        this.assistantId = assistantId;
        this.message = message;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public String getMessage() {
        return message;
    }
}