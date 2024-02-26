package com.latam.companerosDeViajeAPI.persistence.repositories.notification;

import com.latam.companerosDeViajeAPI.persistence.entities.notification.Notification;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.utils.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.user = :user AND n.status = :status")
    List<Notification> findByUserAndStatus(User user, Status status);
    Page<Notification> findByUser(User user, Pageable pageable);
}
