package org.svalero.facilreserva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.svalero.facilreserva.domain.Restaurant;
import org.svalero.facilreserva.domain.dto.ErrorResponse;
import org.svalero.facilreserva.domain.dto.RestaurantInDto;
import org.svalero.facilreserva.domain.dto.RestaurantOutDto;
import org.svalero.facilreserva.service.RestaurantService;
import org.svalero.facilreserva.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants") // Define la ruta base
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    // Obtener todos los restaurantes
    @GetMapping
    public ResponseEntity<List<RestaurantOutDto>> getAll() {
        logger.info("Begin Get all restaurants");
        List<RestaurantOutDto> restaurants = restaurantService.getAll();
        logger.info("End all restaurants");
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // Obtener un restaurante por ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) throws RestaurantNotFoundException {
        logger.info("Begin Get restaurant");
        Restaurant restaurant = restaurantService.get(restaurantId);
        logger.info("Fetching restaurant with id: {}", restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // Agregar un nuevo restaurante
    @PostMapping
    public ResponseEntity<RestaurantOutDto> addRestaurant(@RequestBody RestaurantInDto restaurantInDto) {
        logger.info("Adding new restaurant");
        RestaurantOutDto addRestaurant = restaurantService.add(restaurantInDto);
        logger.info("End adding new restaurant");
        return new ResponseEntity<>(addRestaurant, HttpStatus.CREATED);
    }

    // Modificar un restaurante
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantOutDto> modifyRestaurant(@PathVariable long restaurantId, @RequestBody RestaurantInDto restaurant)
            throws RestaurantNotFoundException {
        logger.info("Begin Modify restaurant");
        RestaurantOutDto modifiedRestaurant = restaurantService.modify(restaurantId, restaurant);
        return ResponseEntity.ok(modifiedRestaurant);
    }

    // Eliminar un restaurante
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable long restaurantId) throws RestaurantNotFoundException {
        logger.info("Deleting restaurant with id: {}", restaurantId);
        restaurantService.delete(restaurantId);
        return ResponseEntity.noContent().build();
    }

    // Manejo de excepción: Restaurante no encontrado
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {
        ErrorResponse error = ErrorResponse.restaurantError(exception.getMessage());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Manejo de excepciones por validaciones incorrectas
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        logger.error(exception.getMessage(), exception);

        return new ResponseEntity<>(ErrorResponse.validationError(errors), HttpStatus.BAD_REQUEST);
    }

    // Manejo de error genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
