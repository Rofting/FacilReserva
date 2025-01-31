package org.svalero.facilreserva.repository;

import org.springframework.data.repository.CrudRepository;
import org.svalero.facilreserva.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
    List<Restaurant> findByName(String name);
    List<Restaurant> findByAddress(String address);
}
