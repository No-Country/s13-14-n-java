package com.latam.companerosDeViajeAPI.service.user;

import com.latam.companerosDeViajeAPI.dto.user.UserDto;
import com.latam.companerosDeViajeAPI.dto.user.UserUpdateDto;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.service.country.CountryService;
import com.latam.companerosDeViajeAPI.service.interest.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CountryService countryService;
    private final InterestService interestService;

    public UserDto getUserDetails(){

        User user = getUser();
        List<Interest> userInterest = user.getInterest();
        List<String> interestName = new ArrayList<>();
        for (Interest interest : userInterest){

            interestName.add(interest.getName());

        }
        return new UserDto(user, interestName);
    }

    public UserDto updateUser(UserUpdateDto userUpdateDto){
        User user = getUser();
        user.setName(userUpdateDto.name());
        user.setEmail(userUpdateDto.email());
        user.setPhoneNumber(userUpdateDto.phoneNumber());
        user.setAddress(userUpdateDto.address());
        user.setBirthDate(userUpdateDto.birthDate());
        user.setGender(userUpdateDto.gender());
        user.setCountry(countryService.findByCountryName(userUpdateDto.country()));
        user.setInterest(userUpdateDto
                .interest()
                .stream()
                .map(interestService :: findByInterestName)
                .toList());
        return getUserDetails();
    }
    public User getUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserDetails> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return  (User) userOptional.get();
    }


}
