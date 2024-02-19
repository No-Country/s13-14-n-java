package com.latam.companerosDeViajeAPI.dto.travelGroup;

import com.latam.companerosDeViajeAPI.dto.interest.InterestDto;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Getter
public class TravelGroupDTO {
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private String itinerary;
    private BigDecimal budget;
    private List<String> interests;
    private Integer minimumNumberOfMembers;
}
