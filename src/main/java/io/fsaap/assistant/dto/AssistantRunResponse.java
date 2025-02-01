package io.fsaap.assistant.dto;

public class AssistantRunResponse {
    private String status; // Por exemplo: "running", "finished", "function_call"
    private String resultMessage; // A resposta gerada ou mensagem de erro

    // Getters e setters

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getResultMessage() {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
