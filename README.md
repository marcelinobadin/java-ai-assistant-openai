# Project Structure
```
java-ai-assistant-openai/
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── io
    │   │       └── fsaap
    │   │           └── assistant
    │   │               ├── domain
    │   │               │   ├── Assistant.java
    │   │               │   ├── ConversationThread.java
    │   │               │   ├── Message.java
    │   │               │   ├── OpenAIResponse.java
    │   │               │   └── TelegramMessageEvent.java
    │   │               ├── dto
    │   │               │   ├── MessageRequest.java
    │   │               │   └── MessageResponse.java
    │   │               ├── resource
    │   │               │   ├── MessageResource.java
    │   │               │   └── TelegramMessageObserver.java
    │   │               └── service
    │   │                   ├── AssistantService.java
    │   │                   └── OpenAIService.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── io
                └── fsaap
                    └── assistant
                        ├── resource
                        │   └── MessageResourceTest.java
                        └── service
                            ├── AssistantServiceTest.java
                            └── OpenAIServiceTest.java
```
