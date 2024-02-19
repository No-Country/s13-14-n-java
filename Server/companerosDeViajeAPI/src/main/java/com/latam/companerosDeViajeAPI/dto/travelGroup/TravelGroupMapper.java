package com.latam.companerosDeViajeAPI.dto.travelGroup;

import com.latam.companerosDeViajeAPI.dto.interest.InterestMapper;
import com.latam.companerosDeViajeAPI.dto.traveler.TravelerMapper;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelGroupMapper {

    public TravelGroup travelGroupDtoToTravelGroup(TravelGroupDTO travelGroupDTO){

        return TravelGroup.builder()
                .destination(travelGroupDTO.getDestination())
                .departureDate(travelGroupDTO.getDepartureDate())
                .returnDate(travelGroupDTO.getReturnDate())
                .itinerary(travelGroupDTO.getItinerary())
                .budget(travelGroupDTO.getBudget())
                .interests(travelGroupDTO.getInterests().stream().map(InterestMapper::stringToInterest).toList())
                .minimumNumberOfMembers(travelGroupDTO.getMinimumNumberOfMembers())
                .build();
    }
    public TravelGroupCreatedDto travelGroupToTravelGroupCreated(TravelGroup travelGroup){
        return TravelGroupCreatedDto.builder()
                .id(travelGroup.getId())
                .destination(travelGroup.getDestination())
                .departureDate(travelGroup.getDepartureDate())
                .returnDate(travelGroup.getReturnDate())
                .itinerary(travelGroup.getItinerary())
                .budget(travelGroup.getBudget())
                .owner(TravelerMapper.userToTravelerDto(travelGroup.getOwner()))
                .interests(travelGroup.getInterests().stream().map(Interest::getName).toList())
                .minimumNumberOfMembers(travelGroup.getMinimumNumberOfMembers())
                .build();
    }

}
