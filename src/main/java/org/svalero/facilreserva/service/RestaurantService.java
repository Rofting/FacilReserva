package org.svalero.facilreserva.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.Restaurant;
import org.svalero.facilreserva.domain.dto.RestaurantInDto;
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

    public List<RestaurantOutDto> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return modelMapper.map(restaurants, new TypeToken<List<RestaurantOutDto>>() {}.getType());
    }

    public Restaurant get(long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);
    }

    public RestaurantOutDto add(RestaurantInDto restaurantInDto) {
        Restaurant restaurant = modelMapper.map(restaurantInDto, Restaurant.class);
        restaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(restaurant, RestaurantOutDto.class);
    }

    public void delete(long restaurantId) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        restaurantRepository.delete(restaurant);
    }

    public RestaurantOutDto modify(long restaurantId, RestaurantInDto restaurantInDto) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        modelMapper.map(restaurantInDto, restaurant);
        restaurantRepository.save(restaurant);
        return modelMapper.map(restaurant, RestaurantOutDto.class);
    }

}
