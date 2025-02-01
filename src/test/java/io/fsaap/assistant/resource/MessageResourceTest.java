package io.fsaap.assistant.resource;

import io.fsaap.assistant.dto.MessageRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class MessageResourceTest {

    @Test
    void testReceiveMessageCriaNovaThread() {
        MessageRequest request = new MessageRequest();
        request.setAssistantId("assistant-1");
        request.setMessage("Olá, endpoint!");
        request.setThreadId("");  // String vazia indica nova thread

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .when().post("/messages")
            .then()
            .statusCode(200)
            .body("assistantId", Matchers.equalTo("assistant-1"))
            .body("threadId", Matchers.not(Matchers.emptyOrNullString()));
    }

    @Test
    void testReceiveMessageComThreadExistente() {
        // Cria uma nova thread enviando threadId vazio
        MessageRequest request1 = new MessageRequest();
        request1.setAssistantId("assistant-1");
        request1.setMessage("Mensagem inicial");
        request1.setThreadId("");

        String threadId = given()
            .contentType(ContentType.JSON)
            .body(request1)
            .when().post("/messages")
            .then()
            .statusCode(200)
            .extract().path("threadId");

        // Envia outra mensagem utilizando o threadId obtido
        MessageRequest request2 = new MessageRequest();
        request2.setAssistantId("assistant-1");
        request2.setMessage("Mensagem na mesma thread");
        request2.setThreadId(threadId);

        given()
            .contentType(ContentType.JSON)
            .body(request2)
            .when().post("/messages")
            .then()
            .statusCode(200)
            .body("threadId", Matchers.equalTo(threadId));
    }
}
