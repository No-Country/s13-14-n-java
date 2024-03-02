package com.latam.companerosDeViajeAPI.service.UserReport;

import com.latam.companerosDeViajeAPI.dto.userReport.ReportDto;
import com.latam.companerosDeViajeAPI.dto.userReport.UserReportDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserReportService {
    UserReportDto reportUser(ReportDto reportDto, HttpServletRequest request);

    List<UserReportDto> getUserReports();
}
