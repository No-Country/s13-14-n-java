package com.latam.companerosDeViajeAPI.dto.interest;

import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InterestMapper {
    public Interest interestDtoToInterest(InterestDto interestDto){
        return Interest.builder()
                .name(interestDto.getName())
                .build();
    }
    public InterestDto interestToInterestDto(Interest interest){
        return InterestDto.builder()
                .name(interest.getName())
                .build();
    }

    public Interest stringToInterest(String s) {
        return Interest.builder()
                .name(s)
                .build();
    }
}
