package com.latam.companerosDeViajeAPI.persistence.entities.UserReport;

import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User reportedTraveler;
    @ManyToOne
    private User reportingTraveler;
    @Column(name = "message")
    private String message;
    @Column(name="revised_complaint")
    private Boolean revisedComplaint;
}
