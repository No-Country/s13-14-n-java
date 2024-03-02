package com.latam.companerosDeViajeAPI.dto.userReport;


import com.latam.companerosDeViajeAPI.dto.traveler.TravelerMapper;
import com.latam.companerosDeViajeAPI.persistence.entities.UserReport.UserReport;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserReportMapper {
    public UserReportDto userReportToUserReportDto(UserReport userReport){
        return UserReportDto.builder()
                .id(userReport.getId())
                .reportedTraveler(TravelerMapper.userToReportedTravelerDto(userReport.getReportedTraveler()))
                .reportingTraveler(TravelerMapper.userToTravelerDto(userReport.getReportingTraveler()))
                .message(userReport.getMessage())
                .revisedComplaint(userReport.getRevisedComplaint())
                .build();
    }
}
