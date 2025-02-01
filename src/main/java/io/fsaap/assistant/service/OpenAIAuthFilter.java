package io.fsaap.assistant.service;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
public class OpenAIAuthFilter implements ClientRequestFilter {

    @Inject
    @ConfigProperty(name = "openai.api.key")
    String apiKey;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add("Authorization", "Bearer " + apiKey);
    }
}