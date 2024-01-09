package com.tryworkout.backend.domain.reservation.service;

import com.tryworkout.backend.domain.reservation.dto.ReservationCreateDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;

public interface ReservationService {
    ReservationDto createReservation(ReservationCreateDto createDto);

    void deleteReservation(Long reservationId);
}
