package com.latam.companerosDeViajeAPI.service.TravelGroup;

import com.latam.companerosDeViajeAPI.exceptions.BadDataEntry;
import com.latam.companerosDeViajeAPI.exceptions.IsNotGreaterThanZeroException;
import com.latam.companerosDeViajeAPI.exceptions.IsNotUserException;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupCreatedDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupDTO;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupMapper;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.travelGroup.TravelGroupRepository;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.service.interest.InterestService;
import com.latam.companerosDeViajeAPI.service.jwt.JwtService;
import com.latam.companerosDeViajeAPI.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TravelGroupServiceImp implements TravelGroupService{
    private TravelGroupRepository travelGroupRepository;
    private JwtService jwtService;
    private UserRepository userRepository;
    private InterestService interestService;

    public TravelGroupServiceImp(TravelGroupRepository travelGroupRepository, JwtService jwtService, UserRepository userRepository, InterestService interestServiceImp) {
        this.travelGroupRepository = travelGroupRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.interestService = interestServiceImp;
    }

    @Override
    @Transactional
    public TravelGroupCreatedDto createTravelGroup(TravelGroupDTO travelGroupDTO, HttpServletRequest request) {
        //Extract user from token
        User owner = findUserByToken(request);
        //remove duplicate interests
        List<Interest> interests = new ArrayList<>();
        Set<String> temp = new HashSet<String>(travelGroupDTO.getInterests());
        travelGroupDTO.getInterests().clear();
        travelGroupDTO.getInterests().addAll(temp);
        //data validation
        validateTravelGroupData(owner, travelGroupDTO);
        //add existing interests
        for (String i: travelGroupDTO.getInterests()) {
            if(interestService.existInterestByName(i)){
                interests.add(interestService.findInterestByName(i));
            }
        }
        //create travel group
        TravelGroup travelGroup = TravelGroupMapper.travelGroupDtoToTravelGroup(travelGroupDTO);
        //set data to travel group
        travelGroup.setOwner(owner);
        travelGroup.setTravelers(new ArrayList<>());
        travelGroup.setInterests(interests);
        //save travel group in the database and return the information
        return TravelGroupMapper.travelGroupToTravelGroupCreated(travelGroupRepository.save(travelGroup));
    }

    private void validateTravelGroupData(User owner, TravelGroupDTO travelGroupDTO) {
        //validate owner have Role_User
        if (!isUser(owner.getRole()))
            throw new IsNotUserException("Only those with a user role can create travel groups.");
        //validate data not blank not null
        if(travelGroupDTO.getDestination()==null||travelGroupDTO.getDestination().isBlank())
            throw new BadDataEntry("The travel destination cannot be blank or null.", "destination");
        if(travelGroupDTO.getDepartureDate()==null)
            throw new BadDataEntry("The departure date cannot be blank or null.", "departure date");
        if(travelGroupDTO.getReturnDate()==null)
            throw new BadDataEntry("The return date cannot be blank or null.", "return date");
        if(travelGroupDTO.getItinerary()==null||travelGroupDTO.getItinerary().isBlank())
            throw new BadDataEntry("The trip itinerary cannot be blank or null.", "itinerary");
        if(travelGroupDTO.getBudget()==null)
            throw new BadDataEntry("The budget cannot be blank or null.", "budget");
        if(travelGroupDTO.getMinimumNumberOfMembers()==null)
            throw new BadDataEntry("The minimum number of members cannot be blank or null.", "minimum number of members");
        //validate interests not empty
        if(travelGroupDTO.getInterests()==null||travelGroupDTO.getInterests().isEmpty())
            throw new BadDataEntry("A travel group's interest list cannot be empty", "interests");
        for (String s: travelGroupDTO.getInterests()) {
            if (s.isBlank())
                throw new BadDataEntry("At least one of the interests entered is blank", "interests");
        }
        //validate interests exists. If at least one is valid, break. If none is valid, an exception is thrown.
        if(!existOneValidInterest(travelGroupDTO.getInterests()))
            throw new BadDataEntry("The interest entered is not valid.", "interests");
        //validates that the return date is after the departure date.
        if (!compareDates(travelGroupDTO.getDepartureDate(), travelGroupDTO.getReturnDate()))
            throw new BadDataEntry("The return date of the trip must be after the departure date.", "departure date and return date");
        //validates that the budget is a number greater than 0
        if (!isGreaterThanZero(travelGroupDTO.getBudget()))
            throw new IsNotGreaterThanZeroException("The budget must be greater than zero", "budget");
        //validates that the minimum number of travelers is a number greater than 0
        if (!isGreaterThanZero(BigDecimal.valueOf(travelGroupDTO.getMinimumNumberOfMembers())))
            throw new IsNotGreaterThanZeroException("The minimum number of members of a travel group must be greater than zero.", "minimum number of members");
    }

    public User findUserByToken( HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.substring(7);
        String username = jwtService.getUsernameFromToken(token);
        return userRepository.getReferenceByUsername(username);
    }
    public boolean isUser(Role role){
        return role.equals(Role.ROLE_USER);
    }

    public boolean isGreaterThanZero(BigDecimal number){
        return number.compareTo(BigDecimal.ZERO)>0;
    }

    public boolean compareDates(LocalDateTime departureDate, LocalDateTime returnDate){
        return returnDate.isAfter(departureDate);
    }

    public boolean existOneValidInterest(List<String> interests){
        for (String s: interests) {
            if(interestService.existInterestByName(s))
                return true;
        }
        return false;
    }

}
