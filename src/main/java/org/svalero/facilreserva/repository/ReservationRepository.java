package org.svalero.facilreserva.repository;

import org.springframework.data.repository.CrudRepository;
import org.svalero.facilreserva.domain.Reservation;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAll();
    List<Reservation> findById(Long reservationId);
    List<Reservation> findByPersons(Integer persons);
}
