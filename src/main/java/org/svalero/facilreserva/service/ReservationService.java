package org.svalero.facilreserva.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.Restaurant;
import org.svalero.facilreserva.domain.dto.ReservationInDto;
import org.svalero.facilreserva.domain.dto.ReservationOutDto;
import org.svalero.facilreserva.exception.ReservationNotFoundException;
import org.svalero.facilreserva.exception.RestaurantNotFoundException;
import org.svalero.facilreserva.repository.ReservationRepository;
import org.svalero.facilreserva.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationOutDto> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return modelMapper.map(reservations, new TypeToken<List<ReservationOutDto>>() {
        }.getType());
    }

    public Reservation get(long reservationId) throws ReservationNotFoundException {
        return reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
    }

    public ReservationOutDto modify(long reservationId, ReservationInDto reservationInDto) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        modelMapper.map(reservationInDto, reservation);
        reservationRepository.save(reservation);
        return modelMapper.map(reservation, ReservationOutDto.class);
    }

    public void delete(long reservationId) throws ReservationNotFoundException {
        reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.deleteById(reservationId);
    }

    public ReservationOutDto add(long restaurantId, ReservationInDto reservationInDto) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);

        Reservation reservation = modelMapper.map(reservationInDto, Reservation.class);
        reservation.setDateReservation(LocalDate.now());
        reservation.setRestaurant(restaurant);
        Reservation newReservation = reservationRepository.save(reservation);

        return modelMapper.map(newReservation, ReservationOutDto.class);
    }

    public List<ReservationOutDto> getByRestaurantId(long restaurantId) throws RestaurantNotFoundException{
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        List<Reservation> reservations = reservationRepository.findByRestaurant(restaurant);
        return modelMapper.map(reservations, new TypeToken<List<ReservationOutDto>>(){}.getType());
    }
}