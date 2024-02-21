package com.latam.companerosDeViajeAPI.dto.user;

import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.country.Country;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.utils.Gender;
import com.latam.companerosDeViajeAPI.utils.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record UserDto(Long id, String name, String email, String phoneNumber,
                      String address, LocalDate birthDate, Gender gender, String country, List<String> interest,
                      String username, Role role) {
    public UserDto(User user, List<String> interest) {
        this(user.getId(), user.getName(),user.getEmail(),user.getPhoneNumber()
                ,user.getAddress(),user.getBirthDate(),user.getGender(),
                user.getCountry().getName(),interest,user.getUsername(),user.getRole());
    }
}
