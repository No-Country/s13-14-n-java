package com.latam.companerosDeViajeAPI.persistence.repositories.userReport;

import com.latam.companerosDeViajeAPI.persistence.entities.UserReport.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {

}
