package com.latam.companerosDeViajeAPI.persistence.repositories.user;

import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUsername(String username);
}
