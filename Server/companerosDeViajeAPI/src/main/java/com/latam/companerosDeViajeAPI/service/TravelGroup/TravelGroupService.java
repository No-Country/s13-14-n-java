package com.latam.companerosDeViajeAPI.service.TravelGroup;

import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupCreatedDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface TravelGroupService {
    public TravelGroupCreatedDto createTravelGroup(TravelGroupDTO travelGroupDTO, HttpServletRequest request);
}
