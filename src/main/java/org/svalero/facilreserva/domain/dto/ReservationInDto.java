package org.svalero.facilreserva.domain.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInDto {

    @NotNull(message = "El nombre de la reserva no puede ser nulo")
    private String nameReservation;

    @NotNull(message = "La fecha de la reserva no puede ser nula")
    private LocalDate dateReservation;

    @Min(value = 1, message = "El número de personas debe ser al menos 1")
    private int persons;

    @NotNull(message = "El estado de confirmación no puede ser nulo")
    private Boolean confirmation;

    @NotNull(message = "El ID del restaurante no puede ser nulo")
    private Long restaurantId;

}