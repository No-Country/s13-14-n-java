package com.latam.companerosDeViajeAPI.controller.WebSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/sendNotification")
    @SendTo("/topic/notification")
    public String sendNotification(String message) {
        return message;
    }
}
