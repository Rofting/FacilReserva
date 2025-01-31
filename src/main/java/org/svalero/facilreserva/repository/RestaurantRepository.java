package org.svalero.facilreserva.repository;

import org.springframework.data.repository.CrudRepository;
import org.svalero.facilreserva.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    List<Restaurant> findAll();
    List<Restaurant> findById(Long id);
    List<Restaurant> findByName(String name);
    List<Restaurant> findByAddress(String address);
}
