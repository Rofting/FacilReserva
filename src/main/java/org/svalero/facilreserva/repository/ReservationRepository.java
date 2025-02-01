package org.svalero.facilreserva.repository;

import org.springframework.data.repository.CrudRepository;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAll();
    Optional<Reservation> findById(Long reservationId);
    List<Reservation> findByPersons(Integer persons);
    List<Reservation> findByRestaurant(Restaurant restaurant);
}
