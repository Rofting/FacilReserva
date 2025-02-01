package org.svalero.facilreserva.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.dto.ErrorResponse;
import org.svalero.facilreserva.domain.dto.ReservationInDto;
import org.svalero.facilreserva.domain.dto.ReservationOutDto;
import org.svalero.facilreserva.exception.ReservationNotFoundException;
import org.svalero.facilreserva.exception.RestaurantNotFoundException;
import org.svalero.facilreserva.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    // Obtener todas las reservas
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationOutDto>> getAll() {
        List<ReservationOutDto> reservations = reservationService.getAll();
        logger.info("Fetching all reservations");
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    //VER Reserva de Restaurante
    @GetMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<List<ReservationOutDto>> getReservationRestaurant(@PathVariable("restaurantId") long restaurantId) throws RestaurantNotFoundException {
        logger.info("BEGIN getReservationRestaurant");
        List<ReservationOutDto> reservationRestaurant = reservationService.getByRestaurantId(restaurantId);
        logger.info("END getUserApartaments");
        return new ResponseEntity<>(reservationRestaurant, HttpStatus.OK);
    }

    // Obtener una reserva por ID
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<Reservation> getById(@PathVariable Long reservationId) throws ReservationNotFoundException {
        logger.info("Begin reservation with id");
        Reservation reservation = reservationService.get(reservationId);
        logger.info("End reservation with id");
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    // Agregar una reserva
    @PostMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<ReservationOutDto> addReservation(@PathVariable long restaurantId, @Valid @RequestBody ReservationInDto reservation) {
        logger.info("Adding new reservation");
        ReservationOutDto newReservation = reservationService.add(restaurantId, reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    // Modificar una reserva
    @PutMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationOutDto> modifyReservation(@PathVariable long reservationId, @Valid @RequestBody ReservationInDto reservationInDto) throws ReservationNotFoundException {
        logger.info("Begin modify reservation with id");
        ReservationOutDto modifiedReservation = reservationService.modify(reservationId, reservationInDto);
        return ok(modifiedReservation);
    }

    // Eliminar una reserva
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) throws ReservationNotFoundException {
        logger.info("Deleting reservation with id: {}", reservationId);
        reservationService.delete(reservationId);
        logger.info("end delete reservation");
        return ResponseEntity.noContent().build();
    }

    // Manejo de excepción: Restaurante no encontrado
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {
        ErrorResponse error = ErrorResponse.restaurantError(exception.getMessage());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Manejo de excepciones por validaciones incorrectas
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        logger.error(exception.getMessage(), exception);

        return new ResponseEntity<>(ErrorResponse.validationError(errors), HttpStatus.BAD_REQUEST);
    }

    // Manejo de error genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
