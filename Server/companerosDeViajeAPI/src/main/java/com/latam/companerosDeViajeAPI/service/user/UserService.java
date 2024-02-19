package com.latam.companerosDeViajeAPI.service.user;

import com.latam.companerosDeViajeAPI.dto.user.UserDto;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
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

    public UserDto getUserDetails(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(username);
        Optional<UserDetails> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        User user = (User) userOptional.get();
        List<Interest> userInterest = user.getInterest();
        List<String> interestName = new ArrayList<>();
        for (Interest interest : userInterest){

            interestName.add(interest.getName());

        }
        return new UserDto(user, interestName);
    }
}
