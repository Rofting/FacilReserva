package org.svalero.facilreserva.exception;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException() {
        super("Reservation Not Found");
    }
}
