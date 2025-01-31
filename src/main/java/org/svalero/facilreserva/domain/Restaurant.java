package org.svalero.facilreserva.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique=true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String capacity;

    @Column(nullable = false)
    private Boolean available;

}
