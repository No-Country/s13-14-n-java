package com.latam.companerosDeViajeAPI.service.interest;

import com.latam.companerosDeViajeAPI.dto.interest.InterestDto;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;

public interface InterestService {
    public Interest createInterest(InterestDto interestDto);
    public Interest findInterestByName(String name);
    public Interest findInterestById(Long id);
    public boolean existInterestByName(String name);
}
