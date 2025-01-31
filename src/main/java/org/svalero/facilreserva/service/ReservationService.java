package org.svalero.facilreserva.service;

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
    private modelMapper modelMapper;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }
    public Reservation get(long reservationId) throws ReservationNotFoundException {
        return reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation modify(Reservation reservation) {
        
    }
    public void delete(long reservationId) throws ReservationNotFoundException {
        reservationRepository.deleteById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.deleteById(reservationId);
    }
    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
