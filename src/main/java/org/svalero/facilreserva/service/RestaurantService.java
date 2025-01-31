package org.svalero.facilreserva.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.Restaurant;
import org.svalero.facilreserva.domain.dto.RestaurantOutDto;
import org.svalero.facilreserva.exception.ReservationNotFoundException;
import org.svalero.facilreserva.exception.RestaurantNotFoundException;
import org.svalero.facilreserva.repository.ReservationRepository;
import org.svalero.facilreserva.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<RestaurantOutDto> getAll() {
        return modelMapper.map(restaurantRepository.findAll(), List.class);
    }

    public Restaurant get(long id, R) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);
    }

    public Restaurant add(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delete(long id) throws RestaurantNotFoundException {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException();
        }
        restaurantRepository.deleteById(id);
    }

    public Restaurant modify(long restaurantId, Restaurant restaurant) throws RestaurantNotFoundException {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        modelMapper.map(restaurant, existingRestaurant);
        return restaurantRepository.save(existingRestaurant);
    }

}
