package com.latam.companerosDeViajeAPI.dto.travelGroup;

import com.latam.companerosDeViajeAPI.dto.traveler.TravelerDTO;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class TravelGroupInfoDto {
    private Long id;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private String itinerary;
    private BigDecimal budget;
    private TravelerDTO owner;
    private List<String> interests;
    private Integer minimumNumberOfMembers;
    private List<TravelerDTO> travelers;
    private Integer totalTravelers;
}
