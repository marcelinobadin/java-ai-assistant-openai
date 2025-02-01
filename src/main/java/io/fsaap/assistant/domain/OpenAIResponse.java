package io.fsaap.assistant.domain;

import java.util.Map;

public class OpenAIResponse {
    private boolean functionCall;
    private String functionName;
    private Map<String, Object> functionParams;
    private String message;

    public OpenAIResponse(boolean functionCall, String functionName, Map<String, Object> functionParams, String message) {
        this.functionCall = functionCall;
        this.functionName = functionName;
        this.functionParams = functionParams;
        this.message = message;
    }

    public boolean isFunctionCall() {
        return functionCall;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Map<String, Object> getFunctionParams() {
        return functionParams;
    }

    public String getMessage() {
        return message;
    }
}
