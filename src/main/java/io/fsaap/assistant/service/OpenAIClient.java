package io.fsaap.assistant.service;

import io.fsaap.assistant.dto.OpenAIRequest;
import io.fsaap.assistant.dto.OpenAIResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/chat/completions")
@RegisterRestClient(configKey = "openai")
@RegisterProvider(OpenAIAuthFilter.class)
public interface OpenAIClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    OpenAIResponse createChatCompletion(OpenAIRequest request);
}