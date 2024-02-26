package com.latam.companerosDeViajeAPI.service.notification;

import com.latam.companerosDeViajeAPI.exceptions.NoSuchNotificationException;
import com.latam.companerosDeViajeAPI.persistence.entities.notification.Notification;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.notification.NotificationRepository;
import com.latam.companerosDeViajeAPI.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(User user, String msg){
        Notification notification = notificationRepository.save(new Notification(msg,user));
        messagingTemplate.convertAndSendToUser(notification.getUser().getUsername(),"/topic/notification", notification.getMsg());
    }

    public Integer getUnreadNotificationCountForUser(User user) {
        List<Notification> UnreadUserNotifications = notificationRepository.findByUserAndStatus(user, Status.NOTREAD);
        return UnreadUserNotifications.size();
    }
    public String MarkNotificationAsRead(Long NotificationId){
        Optional<Notification> optionalNotification = notificationRepository.findById(NotificationId);
        Notification notification = null;
        if(optionalNotification.isPresent()){
            notification = optionalNotification.get();
        }else {
            throw new NoSuchNotificationException("This notification does´not exist");
        }
        notification.setStatus(Status.READ);
        return " Status changed";
    }

    public Page<Notification> getNotificationsByUser(User user, Pageable pageable){
        return notificationRepository.findByUser(user, pageable);
    }


}
