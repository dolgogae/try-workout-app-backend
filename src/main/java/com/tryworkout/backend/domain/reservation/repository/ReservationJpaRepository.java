package com.tryworkout.backend.domain.reservation.repository;

import com.tryworkout.backend.domain.reservation.data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
