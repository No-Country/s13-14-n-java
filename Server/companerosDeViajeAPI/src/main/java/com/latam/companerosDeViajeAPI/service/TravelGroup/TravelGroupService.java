package com.latam.companerosDeViajeAPI.service.TravelGroup;

import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupCreatedDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupDTO;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupInfoDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.UpdateTravelGroupInfoDto;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TravelGroupService {
    public TravelGroupCreatedDto createTravelGroup(TravelGroupDTO travelGroupDTO, HttpServletRequest request);

    Page<TravelGroup> findTravelGroups(Pageable pageable, String destination, LocalDateTime departureDate, LocalDateTime returnDate, BigDecimal budget);

    TravelGroupInfoDto addUserToTravelGroup(Long groupId, HttpServletRequest request);

    TravelGroupInfoDto leaveTravelGroup(Long groupId, HttpServletRequest request);

    String convertOwnerOnTraveler();

    TravelGroupInfoDto updateTravelGroup(Long groupId, HttpServletRequest request, UpdateTravelGroupInfoDto updateTravelGroupInfoDto);
}
