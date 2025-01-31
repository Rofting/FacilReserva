package org.svalero.facilreserva.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.domain.dto.ReservationInDto;
import org.svalero.facilreserva.domain.dto.ReservationOutDto;
import org.svalero.facilreserva.exception.ReservationNotFoundException;
import org.svalero.facilreserva.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationOutDto> getAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return modelMapper.map(reservations, new TypeToken<List<ReservationOutDto>>() {}.getType());
    }

    public ReservationOutDto get(long reservationId) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
        return modelMapper.map(reservation, ReservationOutDto.class);
    }

    public ReservationOutDto modify(long reservationId, ReservationInDto reservationInDto) throws ReservationNotFoundException {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        modelMapper.map(reservationInDto, existingReservation);
        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return modelMapper.map(updatedReservation, ReservationOutDto.class);
    }

    public void delete(long reservationId) throws ReservationNotFoundException {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationNotFoundException();
        }
        reservationRepository.deleteById(reservationId);
    }

    public ReservationOutDto add(ReservationInDto reservationInDto) {
        Reservation reservation = modelMapper.map(reservationInDto, Reservation.class);
        Reservation newReservation = reservationRepository.save(reservation);
        return modelMapper.map(newReservation, ReservationOutDto.class);
    }
}
