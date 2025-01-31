package org.svalero.facilreserva.controller;

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

    // Obtener todas las reservas
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAll();
    }

    // Agregar una reserva
    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.add(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    // Modificar una reserva
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> modifyReservation(@PathVariable long reservationId, @RequestBody Reservation reservation)
            throws ReservationNotFoundException {
        Reservation modifiedReservation = reservationService.modify(reservationId, reservation);
        return new ResponseEntity<>(modifiedReservation, HttpStatus.OK);
    }

    // Eliminar una reserva
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) throws ReservationNotFoundException {
        reservationService.remove(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
