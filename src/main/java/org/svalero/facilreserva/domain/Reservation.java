package org.svalero.facilreserva.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nameReservation;

    @Column(nullable = false)
    private String dateReservation;

    @Column(nullable = false)
    private int persons;

    @Column(nullable = false)
    private Boolean confirmation;

}
