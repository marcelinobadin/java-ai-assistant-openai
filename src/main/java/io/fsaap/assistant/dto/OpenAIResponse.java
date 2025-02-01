package io.fsaap.assistant.dto;

import java.util.List;

public class OpenAIResponse {
    private String id;
    private String object;
    private long created;
    private List<Choice> choices;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public static class Choice {
        private Message message;
        private String finishReason;
        private int index;

        public Message getMessage() {
            return message;
        }

        public String getFinishReason() {
            return finishReason;
        }

        public int getIndex() {
            return index;
        }
    }

    public static class Message {
        private String role;
        private String content;

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }
}
