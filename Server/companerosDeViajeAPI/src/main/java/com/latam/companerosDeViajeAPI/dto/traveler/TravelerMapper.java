package com.latam.companerosDeViajeAPI.dto.traveler;

import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelerMapper {
    public TravelerDTO userToTravelerDto(User user){
        return TravelerDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .profilePicture(user.getProfilePicture())
                .build();
    }


    public ReportedTravelerDTO userToReportedTravelerDto(User reportedTraveler) {
        return ReportedTravelerDTO.builder()
                .id(reportedTraveler.getId())
                .name(reportedTraveler.getName())
                .birthDate(reportedTraveler.getBirthDate())
                .email(reportedTraveler.getEmail())
                .phoneNumber(reportedTraveler.getPhoneNumber())
                .build();
    }
}
