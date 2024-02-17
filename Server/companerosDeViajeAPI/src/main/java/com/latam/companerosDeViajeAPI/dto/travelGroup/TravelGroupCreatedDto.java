package com.latam.companerosDeViajeAPI.dto.travelGroup;

import com.latam.companerosDeViajeAPI.dto.interest.InterestDto;
import com.latam.companerosDeViajeAPI.dto.traveler.TravelerDTO;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;

import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Getter
public class TravelGroupCreatedDto {
    private Long id;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private String itinerary;
    private BigDecimal budget;
    private TravelerDTO owner;
    private List<String> interests;
    private Integer minimumNumberOfMembers;
}
