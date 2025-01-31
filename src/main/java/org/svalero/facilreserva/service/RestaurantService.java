package org.svalero.facilreserva.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.Restaurant;
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
    ReservationRepository reservationRepository;

    public List<Restaurant> getAll() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
    }
    public Restaurant get(long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }

    public Restaurant add(long reservationId, Restaurant restaurant) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        restaurant.setReservations(reservation);
        return restaurantRepository.save(restaurant);
    }

    public void remove(long id) throws ReservationNotFoundException {
        restaurantRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.deleteById(id);
    }
    public Restaurant modify(long restaurantId, Restaurant restaurant) throws ReservationNotFoundException {
        Restaurant restaurant1 = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);

        modelMapper.map(restaurant, restaurant1);
        restaurantRepository.save(restaurant1);
        return modelMapper.map(restaurant1, Restaurant.class);
    }
}
