package com.latam.companerosDeViajeAPI.controller.notification;

import com.latam.companerosDeViajeAPI.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> MarkNotificationAsRead(@PathVariable Long id){
        return ResponseEntity.ok(notificationService.MarkNotificationAsRead(id));
    }
}
