package com.latam.companerosDeViajeAPI.dto.travelGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTravelGroupInfoDto {
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private String itinerary;
    private BigDecimal budget;
    private List<String> interests;
    private Integer minimumNumberOfMembers;
}
