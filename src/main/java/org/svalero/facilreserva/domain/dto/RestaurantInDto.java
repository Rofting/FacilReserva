package org.svalero.facilreserva.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantInDto {

    @NotBlank(message = "El nombre del restaurante es obligatorio")
    private String name;

    @NotBlank(message = "La dirección del restaurante es obligatoria")
    private String address;

    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private int capacity;

    @NotNull(message = "El estado de disponibilidad es obligatorio")
    private Boolean available;
}