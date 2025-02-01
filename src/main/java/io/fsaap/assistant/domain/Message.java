package io.fsaap.assistant.domain;

import java.time.LocalDateTime;

public class Message {
    private String id;
    private String senderId;
    private String text;
    private LocalDateTime timestamp;

    public Message(String id, String senderId, String text) {
        this.id = id;
        this.senderId = senderId;
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
