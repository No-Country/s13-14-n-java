package com.latam.companerosDeViajeAPI.service.interest;

import com.latam.companerosDeViajeAPI.persistence.entities.interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.repositories.interest.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    public Interest findByInterestName (Interest interest){
        return interestRepository.findByName(interest.getName());
    }
}
