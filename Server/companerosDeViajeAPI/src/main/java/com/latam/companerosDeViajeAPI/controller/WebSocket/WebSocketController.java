package com.latam.companerosDeViajeAPI.controller.WebSocket;

import com.latam.companerosDeViajeAPI.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final NotificationService notificationService;

    @MessageMapping("/getUnreadNotificationCount/{userId}")
    @SendTo("/topic/getUnreadNotificationCount/{userId}")
    public Integer getUnreadNotificationCount (@DestinationVariable Long userId){
        return notificationService.getUnreadNotificationCountByUser(userId);
    }
}
