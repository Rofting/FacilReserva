package org.svalero.facilreserva.controller;

import org.svalero.facilreserva.domain.Restaurant;
import org.svalero.facilreserva.service.RestaurantService;
import org.svalero.facilreserva.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.add(restaurant);
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> modifyRestaurant(@PathVariable long restaurantId, @RequestBody Restaurant restaurant)
            throws RestaurantNotFoundException {
        Restaurant modifiedRestaurant = restaurantService.modify(restaurantId, restaurant);
        return new ResponseEntity<>(modifiedRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable long restaurantId) throws RestaurantNotFoundException {
        restaurantService.delete(restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
