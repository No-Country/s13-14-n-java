package com.latam.companerosDeViajeAPI.service.auth;

import com.latam.companerosDeViajeAPI.dto.auth.AuthResponseDto;
import com.latam.companerosDeViajeAPI.dto.auth.LoginRequestDto;
import com.latam.companerosDeViajeAPI.dto.auth.RegisterRequestDto;
import com.latam.companerosDeViajeAPI.exceptions.UsernameOrPasswordIncorretException;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.service.country.CountryService;
import com.latam.companerosDeViajeAPI.service.interest.InterestService;
import com.latam.companerosDeViajeAPI.service.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final CountryService countryService;
    private final InterestService interestService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;



    public AuthResponseDto login(@Valid LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
            );
        } catch (AuthenticationException e) {
            throw new UsernameOrPasswordIncorretException("incorrect username or password ");
        }
        UserDetails user = userRepository.findByUsername(loginRequestDto.username()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthResponseDto(token);
    }

    public AuthResponseDto register(@Valid RegisterRequestDto registerRequestDto){
        User user = new User();
        user.setName(registerRequestDto.name());
        user.setEmail(registerRequestDto.email());
        user.setPhoneNumber(registerRequestDto.phoneNumber());
        user.setAddress(registerRequestDto.address());
        user.setGender(registerRequestDto.gender());
        user.setCountry(countryService.findByCountryName(registerRequestDto.country()));
        user.setInterest(registerRequestDto
                .interest()
                .stream()
                .map(interestService :: findByInterestName)
                .toList());
        user.setUsername(registerRequestDto.username());
        user.setPassword(passwordEncoder.encode(registerRequestDto.password()));
        user.setRole(registerRequestDto.role());
        userRepository.save(user);
        String token = jwtService.getToken(user);
        return new AuthResponseDto(token);




    }

}
