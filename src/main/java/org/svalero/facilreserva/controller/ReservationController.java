package org.svalero.facilreserva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.exception.ReservationNotFoundException;
import org.svalero.facilreserva.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        logger.info("Fetching all reservations");
        return ResponseEntity.ok(reservationService.getAll());
    }

    // Obtener una reserva por ID
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getById(@PathVariable Long reservationId) throws ReservationNotFoundException {
        logger.info("Fetching reservation with id: {}", reservationId);
        return ResponseEntity.ok(reservationService.get(reservationId));
    }

    // Agregar una reserva
    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        logger.info("Adding new reservation");
        Reservation newReservation = reservationService.add(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
    }

    // Modificar una reserva
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> modifyReservation(@PathVariable long reservationId, @RequestBody Reservation reservation)
            throws ReservationNotFoundException {
        logger.info("Modifying reservation with id: {}", reservationId);
        Reservation modifiedReservation = reservationService.modify(reservationId, reservation);
        return ResponseEntity.ok(modifiedReservation);
    }

    // Eliminar una reserva
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) throws ReservationNotFoundException {
        logger.info("Deleting reservation with id: {}", reservationId);
        reservationService.delete(reservationId);
        return ResponseEntity.noContent().build();
    }
}
