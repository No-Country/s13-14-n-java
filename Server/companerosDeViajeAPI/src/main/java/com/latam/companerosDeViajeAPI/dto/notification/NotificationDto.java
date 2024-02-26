package com.latam.companerosDeViajeAPI.dto.notification;

import com.latam.companerosDeViajeAPI.persistence.entities.notification.Notification;
import com.latam.companerosDeViajeAPI.utils.Status;

public record NotificationDto(Long id, String msg, Status status) {

    public NotificationDto(Notification notification){
        this(notification.getId(), notification.getMsg(),notification.getStatus());
    }
}
