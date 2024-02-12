package com.latam.companerosDeViajeAPI.dto.auth;

import com.latam.companerosDeViajeAPI.utils.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
        @NotBlank
        String firstName,
        @NotBlank
        String Lastname,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        Role role
) {
}
