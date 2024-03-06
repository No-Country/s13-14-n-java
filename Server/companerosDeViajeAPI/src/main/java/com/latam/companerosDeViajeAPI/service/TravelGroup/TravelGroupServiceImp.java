package com.latam.companerosDeViajeAPI.service.TravelGroup;


import com.latam.companerosDeViajeAPI.dto.travelGroup.*;
import com.latam.companerosDeViajeAPI.exceptions.*;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import com.latam.companerosDeViajeAPI.persistence.repositories.travelGroup.TravelGroupRepository;
import com.latam.companerosDeViajeAPI.persistence.repositories.user.UserRepository;
import com.latam.companerosDeViajeAPI.service.interest.InterestService;
import com.latam.companerosDeViajeAPI.service.jwt.JwtService;
import com.latam.companerosDeViajeAPI.service.notification.NotificationService;
import com.latam.companerosDeViajeAPI.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private NotificationService notificationService;



    public TravelGroupServiceImp(TravelGroupRepository travelGroupRepository, JwtService jwtService, UserRepository userRepository, InterestService interestServiceImp, NotificationService notificationService) {
        this.travelGroupRepository = travelGroupRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.interestService = interestServiceImp;
        this.notificationService = notificationService;

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
        travelGroup.getTravelers().add(owner);
        travelGroup.setInterests(interests);
        travelGroup.setComplete(false);
        travelGroup.setNegotiationIsOver(false);
        //save travelGroup
        TravelGroup travelGroupCreated =  travelGroupRepository.save(travelGroup);
        notificationService.SendNotificationToAllUsersWithMatchingInterestInTravelGroupCreated(travelGroupCreated);
        // return the information
        return TravelGroupMapper.travelGroupToTravelGroupCreated(travelGroupCreated);

    }

    @Override
    public Page<TravelGroup> findTravelGroups(Pageable pageable, String destination
            , LocalDateTime departureDate, LocalDateTime returnDate, BigDecimal budget) {
        if(isNotNull(destination) && isNotNull(departureDate) && isNotNull(returnDate) && isNotNull(budget) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndDepartureDateAndReturnDateAndBudgetAndNegotiationIsOver(pageable, destination, departureDate, returnDate, budget, false);
        }
        if(isNotNull(destination) && isNotNull(departureDate) && isNotNull(returnDate) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndDepartureDateAndReturnDateAndNegotiationIsOver(pageable, destination, departureDate, returnDate, false);
        }
        if(isNotNull(destination) && isNotNull(departureDate) && isNotNull(budget) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndDepartureDateAndBudgetAndNegotiationIsOver(pageable, destination, departureDate, budget, false);
        }
        if(isNotNull(destination) && isNotNull(returnDate) && isNotNull(budget) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndReturnDateAndBudgetAndNegotiationIsOver(pageable, destination, returnDate, budget, false);
        }
        if(isNotNull(departureDate) && isNotNull(returnDate) && isNotNull(budget)){
            return travelGroupRepository.findByDepartureDateAndReturnDateAndBudgetAndNegotiationIsOver(pageable , departureDate, returnDate, budget, false);
        }
        if(isNotNull(destination) && isNotNull(departureDate) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndDepartureDateAndNegotiationIsOver(pageable, destination, departureDate, false);
        }
        if(isNotNull(destination) && isNotNull(returnDate) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndReturnDateAndNegotiationIsOver(pageable, destination, returnDate, false);
        }
        if(isNotNull(destination) && isNotNull(budget) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndBudgetAndNegotiationIsOver(pageable, destination, budget, false);
        }
        if(isNotNull(departureDate) && isNotNull(returnDate)){
            return travelGroupRepository.findByDepartureDateAndReturnDateAndNegotiationIsOver(pageable, departureDate, returnDate, false);
        }
        if(isNotNull(departureDate) && isNotNull(budget)){
            return travelGroupRepository.findByDepartureDateAndBudgetAndNegotiationIsOver(pageable, departureDate, budget, false);
        }
        if(isNotNull(returnDate) && isNotNull(budget)){
            return travelGroupRepository.findByReturnDateAndBudgetAndNegotiationIsOver(pageable, returnDate, budget, false);
        }
        if(isNotNull(destination) && !destination.isBlank()){
            return travelGroupRepository.findByDestinationAndNegotiationIsOver(pageable, destination, false);
        }
        if(isNotNull(departureDate)){
            return travelGroupRepository.findByDepartureDateAndNegotiationIsOver(pageable, departureDate, false);
        }
        if(isNotNull(returnDate)){
            return travelGroupRepository.findByReturnDateAndNegotiationIsOver(pageable, returnDate, false);
        }
        if(isNotNull(budget)){
            return travelGroupRepository.findByBudgetAndNegotiationIsOver(pageable, budget, false);
        }
        return travelGroupRepository.findByNegotiationIsOver(pageable, false);
    }
    public boolean isNotNull(Object o){
        return o != null;
    }

    @Override
    @Transactional
    public TravelGroupInfoDto addUserToTravelGroup(Long groupId, HttpServletRequest request) {
        User userToAdd = findUserByToken(request);
        validateGroupById(groupId);
        TravelGroup travelGroup = travelGroupRepository.findById(groupId).get();
        validateAddedUserData(userToAdd, travelGroup);
        travelGroup.getTravelers().add(userToAdd);
        if(travelGroup.getMinimumNumberOfMembers() == travelGroup.getTravelers().size()){
            travelGroup.setComplete(true);
        }
        return TravelGroupMapper.travelGroupToTravelGroupInfoDTO(travelGroupRepository.save(travelGroup));
    }

    @Override
    @Transactional
    public TravelGroupInfoDto leaveTravelGroup(Long groupId, HttpServletRequest request) {
        User user = findUserByToken(request);
        validateGroupById(groupId);
        TravelGroup travelGroup = travelGroupRepository.findById(groupId).get();
        validateTravelGroupAbandonmentData(user, travelGroup);
        travelGroup.getTravelers().removeIf(u -> user.getId().equals(u.getId()));
        if(isOwner(user.getId(), travelGroup.getOwner().getId())&&!travelGroup.getTravelers().isEmpty()){
                travelGroup.setOwner(travelGroup.getTravelers().get(0));
        }
        if(travelGroup.getMinimumNumberOfMembers() > travelGroup.getTravelers().size()){
            travelGroup.setComplete(false);
        }
        return TravelGroupMapper.travelGroupToTravelGroupInfoDTO(travelGroupRepository.save(travelGroup));
    }

    @Override
    @Transactional
    public String convertOwnerOnTraveler() {
        List<TravelGroup> travelGroups = travelGroupRepository.findAll();
        for (TravelGroup tg: travelGroups) {
            if(!tg.getTravelers().contains(tg.getOwner())) {
                tg.getTravelers().add(tg.getOwner());
                travelGroupRepository.save(tg);
            }
        }
        return "updated travel group list";
    }

    @Override
    @Transactional
    public TravelGroupInfoDto updateTravelGroup(Long groupId, HttpServletRequest request, UpdateTravelGroupInfoDto updateTravelGroupInfoDto) {
        User user = findUserByToken(request);
        validateGroupById(groupId);
        TravelGroup travelGroup = travelGroupRepository.findById(groupId).get();
        validateTravelGroupUpdateData(user, travelGroup, updateTravelGroupInfoDto);
        updateTravelGroupInfo(travelGroup, updateTravelGroupInfoDto);

        return TravelGroupMapper.travelGroupToTravelGroupInfoDTO(travelGroupRepository.save(travelGroup));
    }

    @Override
    public TravelGroupInfoDto findTravelGroupById(Long id) {
        System.out.println("ID:::::::::: " + id);
        if(!isNotNull(id)){
            throw new BadDataEntryException("The id entered is invalid or null.", "id");
        }
        if(!travelGroupRepository.existsById(id))
            throw new BadDataEntryException("There is no travel group with the id entered: " + id, "id");
        return TravelGroupMapper.travelGroupToTravelGroupInfoDTO(travelGroupRepository.findById(id).get());
    }

    @Override
    public Page<TravelGroup> findTravelGroups(Pageable pageable) {
        return travelGroupRepository.findAll(pageable);
    }

    @Override
    public Page<TravelGroup> findTravelGroupsByOwner(Pageable pageable, HttpServletRequest request) {
        User owner = findUserByToken(request);
        Page<TravelGroup> travelGroups = travelGroupRepository.findByOwner(pageable, owner);
        if(!travelGroups.hasContent()){
            throw new NoTravelGroupsCreatedException("You haven't created a travel group yet.");
        }
        return travelGroups;
    }

    @Override
    public Page<TravelGroup> findCompletesTravelGroups(Pageable pageable) {
        Page<TravelGroup> travelGroupsPages = travelGroupRepository.findByCompleteAndNegotiationIsOver(true ,false,pageable);
        if(!travelGroupsPages.hasContent()){
            throw new NoCompleteTravelGroupsException("There are no fully booked travel groups");
        }
        return travelGroupsPages;

    }

    @Override
    @Transactional
    public String markTravelGroupAsNegotiated(Long travelGroupId) {
        TravelGroup travelGroup = travelGroupRepository.getReferenceById(travelGroupId);
        travelGroup.setNegotiationIsOver(true);
        for (User user : travelGroup.getTravelers()){
            String msg = "Felicidades, hemos llegado a un acuerdo para que tu y tus compañeros de viaje se dirigan a "+ travelGroup.getDestination()
                    +" con una agencia," +
                    " En el transcurso del dia se estaran comunicando con ustedes";
                notificationService.saveNotification(msg,user);
        }
        return "Travel group has been negotiated, travelers will be notified";
    }

    private void updateTravelGroupInfo(TravelGroup travelGroup, UpdateTravelGroupInfoDto updateTravelGroupInfoDto) {
        if(updateTravelGroupInfoDto.getDepartureDate()!=null){
            travelGroup.setDepartureDate(updateTravelGroupInfoDto.getDepartureDate());
        }
        if(updateTravelGroupInfoDto.getReturnDate()!=null){
            travelGroup.setReturnDate(updateTravelGroupInfoDto.getReturnDate());
        }
        if(updateTravelGroupInfoDto.getItinerary()!=null&&!updateTravelGroupInfoDto.getItinerary().isBlank()){
            travelGroup.setItinerary(updateTravelGroupInfoDto.getItinerary());
        }
        if(updateTravelGroupInfoDto.getBudget()!=null){
            travelGroup.setBudget(updateTravelGroupInfoDto.getBudget());
        }
        if(updateTravelGroupInfoDto.getInterests()!=null){
            List<Interest> interests = new ArrayList<>();
            Set<String> temp = new HashSet<String>(updateTravelGroupInfoDto.getInterests());
            updateTravelGroupInfoDto.getInterests().clear();
            updateTravelGroupInfoDto.getInterests().addAll(temp);
            for (String i: updateTravelGroupInfoDto.getInterests()) {
                if(interestService.existInterestByName(i)){
                    interests.add(interestService.findInterestByName(i));
                }
            }
            travelGroup.setInterests(interests);
        }
        if(updateTravelGroupInfoDto.getMinimumNumberOfMembers()!=null){
            travelGroup.setMinimumNumberOfMembers(updateTravelGroupInfoDto.getMinimumNumberOfMembers());
        }


    }

    private void validateTravelGroupUpdateData(User user, TravelGroup travelGroup, UpdateTravelGroupInfoDto updateTravelGroupInfoDto) {
        if (!isOwner(user.getId(), travelGroup.getOwner().getId())){
            throw  new IsNotOwnerException("Only the owner of a travel group can update their information.", "user");
        }
        if(travelGroup.getNegotiationIsOver())
            throw new TravelGroupClosedException("The travel group with id "+ travelGroup.getId() +" is closed and cannot be edited. This trip was negotiated with a travel company.", "groupId");
        LocalDateTime newDepartureDate = travelGroup.getDepartureDate();
        LocalDateTime newReturnDate = travelGroup.getReturnDate();
        boolean testDates= false;
        if(updateTravelGroupInfoDto.getDepartureDate()!=null){
            newDepartureDate=updateTravelGroupInfoDto.getDepartureDate();
            testDates=true;
        }
        if(updateTravelGroupInfoDto.getReturnDate()!=null){
            newReturnDate=updateTravelGroupInfoDto.getReturnDate();
            testDates=true;
        }
        if(testDates){
            if(!compareDates(newDepartureDate, newReturnDate)){
                throw new BadDataEntryException("The return date of the trip must be after the departure date.", "departureDate, returnDate");
            }
        }
        if(updateTravelGroupInfoDto.getItinerary()!=null){
            if(updateTravelGroupInfoDto.getItinerary().isBlank()){
                throw new BadDataEntryException("The trip itinerary cannot be blank or null.", "itinerary");
            }
        }
        if(updateTravelGroupInfoDto.getBudget()!=null){
            if (!isGreaterThanZero(updateTravelGroupInfoDto.getBudget()))
                throw new IsNotGreaterThanZeroException("The budget must be greater than zero", "budget");
        }
        if(updateTravelGroupInfoDto.getMinimumNumberOfMembers()!=null){
            if (!isGreaterThanZero(BigDecimal.valueOf(updateTravelGroupInfoDto.getMinimumNumberOfMembers()))) {
                throw new IsNotGreaterThanZeroException("The minimum number of members of a travel group must be greater than zero.", "minimum number of members");
            }
        }
        if(updateTravelGroupInfoDto.getInterests()!=null){
           if(!existOneValidInterest(updateTravelGroupInfoDto.getInterests())){
               throw new BadDataEntryException("The interest list must include at least one valid name.", "interests");
           }
        }


    }

    private boolean isOwner(Long userId, Long ownerId) {
        return userId.equals(ownerId);
    }

    private void validateTravelGroupAbandonmentData(User user, TravelGroup travelGroup) {
        if(travelGroup.getNegotiationIsOver())
            throw new TravelGroupClosedException("The travel group with id "+ travelGroup.getId() +" is closed and cannot be abandoned. This trip was negotiated with a travel company.", "groupId");
        if(travelGroup.getTravelers().contains(user)){
            return;
        }
        throw new UserOutsideTheGroupException("The user who tries to leave the travel group is not part of the travelers.", "user");

    }

    private void validateGroupById(Long groupId) {
        if(groupId==null)
            throw new BadDataEntryException("idGroup field cannot be empty or null", "groupId");
        if(!travelGroupRepository.existsById(groupId))
            throw new BadDataEntryException("There is no travel group with the id entered", "groupId");
    }

    private void validateAddedUserData(User userToAdd, TravelGroup travelGroup) {
        if(travelGroup.getNegotiationIsOver())
            throw new TravelGroupClosedException("The travel group with id "+ travelGroup.getId() +" is closed. This trip was negotiated with a travel company.", "groupId");
        if(!isUser(userToAdd.getRole()))
            throw new UserNotValidException("Only those with the User role can join a travel group.");
        for (User u: travelGroup.getTravelers()) {
            if(u.getId().equals(userToAdd.getId()))
                throw new UserNotValidException("The user is already joined to the travel group");
        }

        //TODO validate that the user is not suspended, and that the travel group is active.
    }

    private void validateTravelGroupData(User owner, TravelGroupDTO travelGroupDTO) {
        //validate owner have Role_User
        if (!isUser(owner.getRole()))
            throw new UserNotValidException("Only those with a user role can create travel groups.");
        //validate data not blank not null
        if(travelGroupDTO.getDestination()==null||travelGroupDTO.getDestination().isBlank())
            throw new BadDataEntryException("The travel destination cannot be blank or null.", "destination");
        if(travelGroupDTO.getDepartureDate()==null)
            throw new BadDataEntryException("The departure date cannot be blank or null.", "departure date");
        if(travelGroupDTO.getReturnDate()==null)
            throw new BadDataEntryException("The return date cannot be blank or null.", "return date");
        if(travelGroupDTO.getItinerary()==null||travelGroupDTO.getItinerary().isBlank())
            throw new BadDataEntryException("The trip itinerary cannot be blank or null.", "itinerary");
        if(travelGroupDTO.getBudget()==null)
            throw new BadDataEntryException("The budget cannot be blank or null.", "budget");
        if(travelGroupDTO.getMinimumNumberOfMembers()==null)
            throw new BadDataEntryException("The minimum number of members cannot be blank or null.", "minimum number of members");
        //validate interests not empty
        if(travelGroupDTO.getInterests()==null||travelGroupDTO.getInterests().isEmpty())
            throw new BadDataEntryException("A travel group's interest list cannot be empty", "interests");
        for (String s: travelGroupDTO.getInterests()) {
            if (s.isBlank())
                throw new BadDataEntryException("At least one of the interests entered is blank", "interests");
        }
        //validate interests exists. If at least one is valid, break. If none is valid, an exception is thrown.
        if(!existOneValidInterest(travelGroupDTO.getInterests()))
            throw new BadDataEntryException("The interest list must include at least one valid name.", "interests");
        //validates that the return date is after the departure date.
        if (!compareDates(travelGroupDTO.getDepartureDate(), travelGroupDTO.getReturnDate()))
            throw new BadDataEntryException("The return date of the trip must be after the departure date.", "departure date and return date");
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
    @Transactional
    public String completedNegotiatedFalse() {
        List<TravelGroup> travelGroups = travelGroupRepository.findAll();
        for (TravelGroup tg: travelGroups) {
            tg.setComplete(false);
            tg.setNegotiationIsOver(false);
            travelGroupRepository.save(tg);
        }
        return "updated travel group list";
    }
}
