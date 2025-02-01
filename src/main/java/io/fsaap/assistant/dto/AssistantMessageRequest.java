package io.fsaap.assistant.dto;

public class AssistantMessageRequest {
    private String senderId;
    private String message;

    // Getters e setters

    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
