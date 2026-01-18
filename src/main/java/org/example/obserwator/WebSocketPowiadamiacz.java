package org.example.obserwator;

import org.example.model.Zadanie;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketPowiadamiacz implements ObserwatorStatusuZadania {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketPowiadamiacz(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void aktualizuj(Zadanie zadanie)
    {

        System.out.println("WebSocket: Przesylam akt zadania: " + zadanie.getNazwa());
        messagingTemplate.convertAndSend("/temat/zmiany", zadanie);
    }
}