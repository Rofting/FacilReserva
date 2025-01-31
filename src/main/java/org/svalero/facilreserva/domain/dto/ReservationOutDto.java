package org.svalero.facilreserva.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationOutDto {
    private long id;
    private String nameReservation;
    private LocalDate dateReservation;
    private int persons;
    private Boolean confirmation;
    private long restaurantId;
}