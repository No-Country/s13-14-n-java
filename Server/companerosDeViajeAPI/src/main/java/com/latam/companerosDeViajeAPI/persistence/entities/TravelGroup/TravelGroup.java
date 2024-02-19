package com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup;

import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private String itinerary;
    private BigDecimal budget;
    @ManyToOne
    @JoinColumn
    private User owner;
    @ManyToMany
    private List<User> travelers;
    @ManyToMany
    private List<Interest> interests;
    private Integer minimumNumberOfMembers;
}
