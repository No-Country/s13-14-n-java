package com.latam.companerosDeViajeAPI.controller.notification;

import com.latam.companerosDeViajeAPI.dto.notification.NotificationDto;
import com.latam.companerosDeViajeAPI.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/all")
    public ResponseEntity<Page<NotificationDto>> getAllNotificationByUser(Pageable pageable){
        return  ResponseEntity.ok(notificationService.getNotificationsByUser(pageable));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getUnreadNotificationCount(){
        return ResponseEntity.ok(notificationService.getUnreadNotificationCountForUser());
    }
}
