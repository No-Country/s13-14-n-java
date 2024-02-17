package com.latam.companerosDeViajeAPI.service.country;

import com.latam.companerosDeViajeAPI.dto.auth.RegisterRequestDto;
import com.latam.companerosDeViajeAPI.persistence.entities.country.Country;
import com.latam.companerosDeViajeAPI.persistence.repositories.country.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public Country findByCountryName(Country country){
        return countryRepository.findByName(country.getName());
    }
}
