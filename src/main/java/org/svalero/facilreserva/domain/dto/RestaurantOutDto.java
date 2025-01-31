package org.svalero.facilreserva.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOutDto {

    private long id;
    private String name;
    private String address;
    private int capacity;
    private Boolean available;
}