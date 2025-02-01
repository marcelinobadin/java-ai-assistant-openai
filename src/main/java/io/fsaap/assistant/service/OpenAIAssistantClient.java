package io.fsaap.assistant.service;

import io.fsaap.assistant.dto.AssistantConversationResponse;
import io.fsaap.assistant.dto.AssistantMessageRequest;
import io.fsaap.assistant.dto.AssistantRunResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

@Path("/assistants")
@RegisterRestClient(configKey = "openai")
@RegisterProvider(OpenAIAuthFilter.class)
public interface OpenAIAssistantClient {

    // Cria uma nova conversa (thread) com a primeira mensagem
    @POST
    @Path("/{assistantId}/conversations")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AssistantConversationResponse createConversation(@PathParam("assistantId") String assistantId, AssistantMessageRequest request);

    // Adiciona uma mensagem a uma conversa já existente
    @POST
    @Path("/{assistantId}/conversations/{conversationId}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void addMessage(@PathParam("assistantId") String assistantId, @PathParam("conversationId") String conversationId, AssistantMessageRequest request);

    // Executa (run) a conversa e obtém a resposta do assistente
    @POST
    @Path("/{assistantId}/conversations/{conversationId}/run")
    @Produces(MediaType.APPLICATION_JSON)
    AssistantRunResponse runConversation(@PathParam("assistantId") String assistantId, @PathParam("conversationId") String conversationId);
}
