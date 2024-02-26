package com.latam.companerosDeViajeAPI.service.user;

import com.latam.companerosDeViajeAPI.dto.notification.NotificationDto;
import com.latam.companerosDeViajeAPI.dto.user.UserDto;
import com.latam.companerosDeViajeAPI.dto.user.UserUpdateDto;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.service.country.CountryService;
import com.latam.companerosDeViajeAPI.service.interest.InterestService;
import com.latam.companerosDeViajeAPI.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CountryService countryService;
    private final InterestService interestService;
    private final NotificationService notificationService;

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

    public void SendNotificationToAllUsersWithMatchingInterestInTravelGroupCreated(TravelGroup travelGroup){
        Set<User> usersWithMatchingInterests =  userRepository.findUsersWithMatchingInterestsInGroup(travelGroup);
        for (User user : usersWithMatchingInterests) {
            String msg = "Nuevo grupo de viaje a " + travelGroup.getDestination();
            notificationService.sendNotification(user, msg);
        }
    }
    public Page<NotificationDto> getAllNotificationByUser(Pageable pageable){
        User user = getUser();
        return  notificationService.getNotificationsByUser(user,pageable).map(NotificationDto :: new);
    }
    public Integer GetNotificationCount (){
        User user = getUser();
        return notificationService.getUnreadNotificationCountForUser(user);
    }


    private User getUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserDetails> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return  (User) userOptional.get();
    }


}
