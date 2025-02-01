package io.fsaap.assistant.resource;

import io.fsaap.assistant.dto.MessageRequest;
import io.fsaap.assistant.dto.MessageResponse;
import io.fsaap.assistant.service.AssistantService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    AssistantService assistantService;

    @POST
    public MessageResponse receiveMessage(MessageRequest request) {
        return assistantService.processMessage(request);
    }
}
