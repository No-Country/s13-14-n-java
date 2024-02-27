package com.latam.companerosDeViajeAPI.persistence.repositories.user;

import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username=:username")
    User getReferenceByUsername(String username);
    @Query("SELECT DISTINCT u FROM User u JOIN u.interest i JOIN i.travelGroup g WHERE g = :travelGroup")
    Set<User> findUsersWithMatchingInterestsInGroup(@Param("travelGroup") TravelGroup travelGroup);
}
