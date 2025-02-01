package io.fsaap.assistant.dto;

public class MessageResponse {
    private String assistantId;
    private String threadId;

    public MessageResponse(String assistantId, String threadId) {
        this.assistantId = assistantId;
        this.threadId = threadId;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}
