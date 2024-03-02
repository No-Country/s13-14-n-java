package com.latam.companerosDeViajeAPI.dto.traveler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class ReportedTravelerDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
}
