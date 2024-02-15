package com.latam.companerosDeViajeAPI.persistence.repositories.interest;

import com.latam.companerosDeViajeAPI.persistence.entities.interest.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findByName(@Param("name")String name);
}
