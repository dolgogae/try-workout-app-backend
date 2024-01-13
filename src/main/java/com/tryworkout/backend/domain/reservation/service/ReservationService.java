package com.tryworkout.backend.domain.reservation.service;

import com.tryworkout.backend.domain.reservation.dto.ReservationContentsDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationCreateDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;
import org.springframework.data.domain.Page;

public interface ReservationService {
    ReservationDto createReservation(ReservationCreateDto createDto);
    ReservationDto deleteReservation(Long reservationId);
    Page<ReservationContentsDto> getReservationsByMemberId(Long memberId, int page, int size);
    Page<ReservationContentsDto> getReservationsByTrainerId(Long trainerId, int page, int size);
}
