package com.latam.companerosDeViajeAPI.persistence.entities.Interest;

import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name ="interest")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Getter
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "interest") //
    private List<User> users;
}
