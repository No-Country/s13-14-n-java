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

    Page<TravelGroup> findByDestinationAndDepartureDateAndReturnDateAndBudgetAndNegotiationIsOver(Pageable pageable, String destination, LocalDateTime departureDate, LocalDateTime returnDate, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndDepartureDateAndReturnDateAndNegotiationIsOver(Pageable pageable, String destination, LocalDateTime departureDate, LocalDateTime returnDate, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndDepartureDateAndBudgetAndNegotiationIsOver(Pageable pageable, String destination, LocalDateTime departureDate, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndReturnDateAndBudgetAndNegotiationIsOver(Pageable pageable, String destination, LocalDateTime returnDate, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByDepartureDateAndReturnDateAndBudgetAndNegotiationIsOver(Pageable pageable, LocalDateTime departureDate, LocalDateTime returnDate, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndDepartureDateAndNegotiationIsOver(Pageable pageable, String destination, LocalDateTime departureDate, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndReturnDateAndNegotiationIsOver(Pageable pageable, String destination, LocalDateTime returnDate, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndBudgetAndNegotiationIsOver(Pageable pageable, String destination, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByDepartureDateAndReturnDateAndNegotiationIsOver(Pageable pageable, LocalDateTime departureDate, LocalDateTime returnDate, Boolean negotiationIsOver);

    Page<TravelGroup> findByDepartureDateAndBudgetAndNegotiationIsOver(Pageable pageable, LocalDateTime departureDate, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByReturnDateAndBudgetAndNegotiationIsOver(Pageable pageable, LocalDateTime returnDate, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByDestinationAndNegotiationIsOver(Pageable pageable, String destination, Boolean negotiationIsOver);

    Page<TravelGroup> findByDepartureDateAndNegotiationIsOver(Pageable pageable, LocalDateTime departureDate, Boolean negotiationIsOver);

    Page<TravelGroup> findByReturnDateAndNegotiationIsOver(Pageable pageable, LocalDateTime returnDate, Boolean negotiationIsOver);

    Page<TravelGroup> findByBudgetAndNegotiationIsOver(Pageable pageable, BigDecimal budget, Boolean negotiationIsOver);

    Page<TravelGroup> findByOwner(Pageable pageable, User Owner);

    Page<TravelGroup> findByCompleteAndNegotiationIsOver(boolean complete, boolean negotiationIsOver, Pageable pageable);

    Page<TravelGroup> findByNegotiationIsOver(Pageable pageable, boolean b);
}
