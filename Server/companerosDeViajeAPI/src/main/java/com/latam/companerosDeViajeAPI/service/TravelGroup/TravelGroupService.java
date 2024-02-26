package com.latam.companerosDeViajeAPI.service.TravelGroup;

import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupCreatedDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupDTO;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupInfoDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.UpdateTravelGroupInfoDto;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TravelGroupService {
    public TravelGroupCreatedDto createTravelGroup(TravelGroupDTO travelGroupDTO, HttpServletRequest request);

    Page<TravelGroup> getTravelGroups(Pageable pageable);

    TravelGroupInfoDto addUserToTravelGroup(Long groupId, HttpServletRequest request);

    TravelGroupInfoDto leaveTravelGroup(Long groupId, HttpServletRequest request);

    String convertOwnerOnTraveler();

    TravelGroupInfoDto updateTravelGroup(Long groupId, HttpServletRequest request, UpdateTravelGroupInfoDto updateTravelGroupInfoDto);
}
