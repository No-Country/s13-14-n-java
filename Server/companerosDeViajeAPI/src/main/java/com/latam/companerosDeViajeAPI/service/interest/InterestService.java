package com.latam.companerosDeViajeAPI.service.interest;

import com.latam.companerosDeViajeAPI.exceptions.NoSuchInterestException;
import com.latam.companerosDeViajeAPI.persistence.entities.interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.repositories.interest.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    public Interest findByInterestName (Interest interest){
        Optional<Interest> interestOptional = interestRepository.findByName(interest.getName());

        if (interestOptional.isEmpty()){
            throw new NoSuchInterestException("this interest does not exist");
        }else {
        return interestOptional.get();
        }
    }
}
