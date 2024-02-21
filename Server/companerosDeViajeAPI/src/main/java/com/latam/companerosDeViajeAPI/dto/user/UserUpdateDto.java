package com.latam.companerosDeViajeAPI.dto.user;

import com.latam.companerosDeViajeAPI.persistence.entities.country.Country;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.utils.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserUpdateDto(@NotBlank
                            String name,
                            @NotBlank
                            @Email
                            String email,
                            @NotBlank
                            String phoneNumber,
                            @NotBlank
                            String address,
                            @NotNull
                            LocalDate birthDate,
                            @NotNull
                            Gender gender,
                            @NotNull
                            Country country) {

}
