package org.svalero.facilreserva.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.svalero.facilreserva.domain.Reservation;
import org.svalero.facilreserva.exception.ReservationNotFoundException;
import org.svalero.facilreserva.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Reservation get(long reservationId) throws ReservationNotFoundException {
        return reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation modify(long reservationId, Reservation reservation) throws ReservationNotFoundException {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        modelMapper.map(reservation, existingReservation);
        return reservationRepository.save(existingReservation);
    }

    public void delete(long reservationId) throws ReservationNotFoundException {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationNotFoundException();
        }
        reservationRepository.deleteById(reservationId);
    }

    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
