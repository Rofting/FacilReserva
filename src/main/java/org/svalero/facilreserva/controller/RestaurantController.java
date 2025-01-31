package org.svalero.facilreserva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.svalero.facilreserva.domain.Restaurant;
import org.svalero.facilreserva.service.RestaurantService;
import org.svalero.facilreserva.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants") // Define la ruta base
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    // Obtener todos los restaurantes
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        logger.info("Fetching all restaurants");
        return ResponseEntity.ok(restaurantService.getAll());
    }

    // Obtener un restaurante por ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getById(@PathVariable Long restaurantId) throws RestaurantNotFoundException {
        logger.info("Fetching restaurant with id: {}", restaurantId);
        return ResponseEntity.ok(restaurantService.get(restaurantId));
    }

    // Agregar un nuevo restaurante
    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        logger.info("Adding new restaurant");
        Restaurant newRestaurant = restaurantService.add(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    // Modificar un restaurante
    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> modifyRestaurant(@PathVariable long restaurantId, @RequestBody Restaurant restaurant)
            throws RestaurantNotFoundException {
        logger.info("Modifying restaurant with id: {}", restaurantId);
        Restaurant modifiedRestaurant = restaurantService.modify(restaurantId, restaurant);
        return ResponseEntity.ok(modifiedRestaurant);
    }

    // Eliminar un restaurante
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable long restaurantId) throws RestaurantNotFoundException {
        logger.info("Deleting restaurant with id: {}", restaurantId);
        restaurantService.delete(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
