package com.latam.companerosDeViajeAPI.dto.traveler;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TravelerDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String profilePicture;

}
