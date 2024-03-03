package com.latam.companerosDeViajeAPI.dto.userReport;

import com.latam.companerosDeViajeAPI.dto.traveler.ReportedTravelerDTO;
import com.latam.companerosDeViajeAPI.dto.traveler.TravelerDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserReportDto {
    private Long id;
    private ReportedTravelerDTO reportedTraveler;
    private TravelerDTO reportingTraveler;
    private String message;
    private Boolean revisedComplaint;
}
