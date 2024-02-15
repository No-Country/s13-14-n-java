package com.latam.companerosDeViajeAPI.persistence.repositories.country;

import com.latam.companerosDeViajeAPI.persistence.entities.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(@Param("name") String name);
}
