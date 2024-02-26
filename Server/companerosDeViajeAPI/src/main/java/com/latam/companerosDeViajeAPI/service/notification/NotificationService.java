package com.latam.companerosDeViajeAPI.service.notification;

import com.latam.companerosDeViajeAPI.dto.notification.NotificationDto;
import com.latam.companerosDeViajeAPI.exceptions.NoSuchNotificationException;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.notification.Notification;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.notification.NotificationRepository;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.service.user.UserService;
import com.latam.companerosDeViajeAPI.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;
    private final UserService userService;


    public void SendNotificationToAllUsersWithMatchingInterestInTravelGroupCreated(TravelGroup travelGroup){
        Set<User> usersWithMatchingInterests =  userRepository.findUsersWithMatchingInterestsInGroup(travelGroup);
        for (User user : usersWithMatchingInterests) {
            if(!(travelGroup.getOwner().getUsername().equals(user.getUsername()))){
                String msg = "Se ha creado un nuevo grupo de viaje a " +
                        travelGroup.getDestination() +" que se ajusta con tus preferencias";
                Notification notification = notificationRepository.save(new Notification(msg,user));
                messagingTemplate.convertAndSendToUser(notification.getUser()
                        .getUsername(),"/topic/notification", notification.getMsg());
            }
        }
    }

    public Integer getUnreadNotificationCountForUser() {
        User user = userService.getUser();
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

    public Page<NotificationDto> getNotificationsByUser(Pageable pageable){
        User user = userService.getUser();
        return notificationRepository.findByUser(user, pageable).map(NotificationDto :: new);
    }




}
