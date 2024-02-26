package com.latam.companerosDeViajeAPI.persistence.entities.notification;

import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.utils.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String msg;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Notification(String msg, User user){
        this.msg = msg;
        this.user = user;
        this.status = Status.NOTREAD;
    }

}
