package com.latam.companerosDeViajeAPI.dto.auth;

import com.latam.companerosDeViajeAPI.persistence.entities.country.Country;
import com.latam.companerosDeViajeAPI.utils.Gender;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.utils.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterRequestDto(
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String address,
        @NotNull
        Gender gender,
        @NotNull
        Country country,
        @NotNull
        List<Interest> interest,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        Role role
) {
}
