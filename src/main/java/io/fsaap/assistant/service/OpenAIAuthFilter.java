package io.fsaap.assistant.service;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class OpenAIAuthFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        // Você pode obter a API key de uma variável de ambiente ou configuração
        String apiKey = System.getProperty("openai.api.key", "YOUR_API_KEY");
        requestContext.getHeaders().add("Authorization", "Bearer " + apiKey);
    }
}
