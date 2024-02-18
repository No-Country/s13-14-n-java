package com.latam.companerosDeViajeAPI.persistence.repositories.interest;

import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository <Interest, Long> {

    Optional<Interest> findByName(@Param("name")String name);
    @Query("SELECT i FROM Interest i WHERE i.name=:name")
    Interest getReferenceByName(String name);

    boolean existsByName(String name);

}
