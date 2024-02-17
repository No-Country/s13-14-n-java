package com.latam.companerosDeViajeAPI.service.interest;

import com.latam.companerosDeViajeAPI.dto.interest.InterestDto;
import com.latam.companerosDeViajeAPI.dto.interest.InterestMapper;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.repositories.interest.InterestRepository;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImp implements InterestService{
    private InterestRepository interestRepository;

    public InterestServiceImp(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    @Override
    public Interest createInterest(InterestDto interestDto) {
        Interest interestCreated = InterestMapper.interestDtoToInterest(interestDto);
        return interestRepository.save(interestCreated);
    }

    @Override
    public Interest findInterestByName(String name) {
        return interestRepository.findByName(name);
    }

    @Override
    public Interest findInterestById(Long id) {
        return interestRepository.findById(id).get();
    }

    @Override
    public boolean existInterestByName(String name) {
        return interestRepository.existsByName(name);
    }
}
