package io.fsaap.assistant.observer;

import io.fsaap.assistant.domain.TelegramMessageEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TelegramMessageObserver {

    private static final Logger log = Logger.getLogger(TelegramMessageObserver.class);

    public void onTelegramMessage(@Observes TelegramMessageEvent event) {
        // Aqui você implementaria a integração com o Telegram.
        // Neste exemplo, apenas imprime no console.
        log.info("Enviando mensagem para o Telegram:");
        log.info("AssistantId: " + event.getAssistantId());
        log.info("ThreadId: " + event.getThreadId());
        log.info("Mensagem: " + event.getMessage());
    }
}