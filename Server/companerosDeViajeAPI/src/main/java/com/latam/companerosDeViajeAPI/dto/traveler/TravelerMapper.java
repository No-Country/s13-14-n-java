package com.latam.companerosDeViajeAPI.dto.traveler;

import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelerMapper {
    public TravelerDTO userToTravelerDto(User user){
        return TravelerDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .profilePicture(user.getProfilePicture())
                .build();
    }

}
