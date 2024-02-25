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
    @JoinTable(
            name = "travel_group_interest", //
            joinColumns = @JoinColumn(name = "travel_group_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private List<Interest> interests;
    private Integer minimumNumberOfMembers;
}
