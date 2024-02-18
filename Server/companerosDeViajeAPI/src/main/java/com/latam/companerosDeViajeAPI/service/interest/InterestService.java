package com.latam.companerosDeViajeAPI.service.interest;


import com.latam.companerosDeViajeAPI.dto.interest.InterestDto;
import com.latam.companerosDeViajeAPI.dto.interest.InterestMapper;
import com.latam.companerosDeViajeAPI.exceptions.NoSuchInterestException;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.repositories.interest.InterestRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService{
    private final InterestRepository interestRepository;
    public Interest findByInterestName (Interest interest){
        Optional<Interest> interestOptional = interestRepository.findByName(interest.getName());

        if (interestOptional.isEmpty()){
            throw new NoSuchInterestException("this interest does not exist");
        }else {
        return interestOptional.get();
        }
    }

    public Interest createInterest(InterestDto interestDto) {
        Interest interestCreated = InterestMapper.interestDtoToInterest(interestDto);
        return interestRepository.save(interestCreated);
    }


    public Interest findInterestByName(String name) {
        return interestRepository.getReferenceByName(name);
    }


    public com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest findInterestById(Long id) {
        return interestRepository.findById(id).get();
    }


    public boolean existInterestByName(String name) {
        return interestRepository.existsByName(name);
    }

}
