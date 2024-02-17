package com.latam.companerosDeViajeAPI.persistence.repositories.interest;

import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository <Interest, Long> {
    Interest findByName(String name);

    boolean existsByName(String name);
}
