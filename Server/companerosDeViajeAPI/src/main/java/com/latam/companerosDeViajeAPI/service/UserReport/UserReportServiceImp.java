package com.latam.companerosDeViajeAPI.service.UserReport;

import com.latam.companerosDeViajeAPI.dto.userReport.ReportDto;
import com.latam.companerosDeViajeAPI.dto.userReport.UserReportDto;
import com.latam.companerosDeViajeAPI.dto.userReport.UserReportMapper;
import com.latam.companerosDeViajeAPI.exceptions.BadDataEntryException;
import com.latam.companerosDeViajeAPI.persistence.entities.UserReport.UserReport;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.travelGroup.TravelGroupRepository;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.persistence.repositories.userReport.UserReportRepository;
import com.latam.companerosDeViajeAPI.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserReportServiceImp implements UserReportService{
    private UserRepository userRepository;
    private TravelGroupRepository travelGroupRepository;
    private UserReportRepository userReportRepository;
    private JwtService jwtService;
    @Override
    public UserReportDto reportUser(ReportDto reportDto, HttpServletRequest request) {
        validateData(reportDto);
        UserReport userReport = new UserReport();
        User reportedTraveler = userRepository.getReferenceById(reportDto.getReportedTravelerId());
        User reportingTraveler = findUserByToken(request);
        userReport.setReportedTraveler(reportedTraveler);
        userReport.setReportingTraveler(reportingTraveler);
        if(reportDto.getMessage()==null){
            reportDto.setMessage("");
        }
        userReport.setMessage(reportDto.getMessage());
        userReport.setRevisedComplaint(false);

        return UserReportMapper.userReportToUserReportDto(userReportRepository.save(userReport));
    }

    @Override
    public List<UserReportDto> getUserReports() {
        return userReportRepository.findAll().stream().map(UserReportMapper::userReportToUserReportDto).toList();
    }

    private void validateData(ReportDto reportDto) {
        if(!userRepository.existsById(reportDto.getReportedTravelerId())){
            throw new BadDataEntryException("There is no user with the entered id", "reportedTravelerId");
        }
    }
    public User findUserByToken(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.substring(7);
        String username = jwtService.getUsernameFromToken(token);
        return userRepository.getReferenceByUsername(username);
    }
}
