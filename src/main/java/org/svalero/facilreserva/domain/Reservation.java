package org.svalero.facilreserva.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nameReservation;

    @Column(nullable = false)
    private LocalDate dateReservation;

    @Column(nullable = false)
    private int persons;

    @Column(nullable = false)
    private Boolean confirmation;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
