package com.latam.companerosDeViajeAPI.persistence.repositories.travelGroup;

import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.persistence.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface TravelGroupRepository extends JpaRepository<TravelGroup, Long> {

    Page<TravelGroup> findByDestinationAndDepartureDateAndReturnDateAndBudget(Pageable pageable, String destination, LocalDateTime departureDate, LocalDateTime returnDate, BigDecimal budget);

    Page<TravelGroup> findByDestinationAndDepartureDateAndReturnDate(Pageable pageable, String destination, LocalDateTime departureDate, LocalDateTime returnDate);

    Page<TravelGroup> findByDestinationAndDepartureDateAndBudget(Pageable pageable, String destination, LocalDateTime departureDate, BigDecimal budget);

    Page<TravelGroup> findByDestinationAndReturnDateAndBudget(Pageable pageable, String destination, LocalDateTime returnDate, BigDecimal budget);

    Page<TravelGroup> findByDepartureDateAndReturnDateAndBudget(Pageable pageable, LocalDateTime departureDate, LocalDateTime returnDate, BigDecimal budget);

    Page<TravelGroup> findByDestinationAndDepartureDate(Pageable pageable, String destination, LocalDateTime departureDate);

    Page<TravelGroup> findByDestinationAndReturnDate(Pageable pageable, String destination, LocalDateTime returnDate);

    Page<TravelGroup> findByDestinationAndBudget(Pageable pageable, String destination, BigDecimal budget);

    Page<TravelGroup> findByDepartureDateAndReturnDate(Pageable pageable, LocalDateTime departureDate, LocalDateTime returnDate);

    Page<TravelGroup> findByDepartureDateAndBudget(Pageable pageable, LocalDateTime departureDate, BigDecimal budget);

    Page<TravelGroup> findByReturnDateAndBudget(Pageable pageable, LocalDateTime returnDate, BigDecimal budget);

    Page<TravelGroup> findByDestination(Pageable pageable, String destination);

    Page<TravelGroup> findByDepartureDate(Pageable pageable, LocalDateTime departureDate);

    Page<TravelGroup> findByReturnDate(Pageable pageable, LocalDateTime returnDate);

    Page<TravelGroup> findByBudget(Pageable pageable, BigDecimal budget);

    Page<TravelGroup> findByOwner(Pageable pageable, User Owner);

    Page<TravelGroup> findByCompleteAndNegotiationIsOver(boolean complete, boolean negotiationIsOver, Pageable pageable);

}
