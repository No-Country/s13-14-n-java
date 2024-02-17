package com.latam.companerosDeViajeAPI.persistence.repositories.user;

import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=:username")
    User getReferenceByUsername(String username);
}
