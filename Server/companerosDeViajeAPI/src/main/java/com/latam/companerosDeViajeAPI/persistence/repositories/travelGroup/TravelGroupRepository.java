package com.latam.companerosDeViajeAPI.persistence.repositories.travelGroup;

import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelGroupRepository extends JpaRepository<TravelGroup, Long> {

}
